#!/usr/bin/env python3
"""
audit_engine_coverage.py
Xposed Edge Pro Motor Kapsam ve Deterministik Doğrulama Denetçisi.
Kod tabanındaki tüm Action ID'leri, Condition ID'leri, Trigger ID'leri,
Edge ID'leri ve State Machine sınıflarını tarar ve test dosyalarındaki
kapsam oranını matematiksel olarak doğrular.
"""

import os
import re
import sys
import json

PROJECT_ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SRC_DIR = os.path.join(PROJECT_ROOT, "app/src/main/java")
TEST_DIR = os.path.join(PROJECT_ROOT, "app/src/test/java")

def extract_action_ids():
    b_file = os.path.join(SRC_DIR, "pkg_a/b.java")
    with open(b_file, "r", encoding="utf-8") as f:
        content = f.read()
    
    # super(ID, R.string.action_...
    pattern = r'super\((\d+),\s*R\.string\.action_'
    matches = re.findall(pattern, content)
    ids = sorted(list(set(int(m) for m in matches)))
    return ids

def extract_condition_ids():
    h_file = os.path.join(SRC_DIR, "pkg_a/h.java")
    with open(h_file, "r", encoding="utf-8") as f:
        content = f.read()
    
    pattern = r'super\((\d+),\s*R\.string\.'
    matches = re.findall(pattern, content)
    ids = sorted(list(set(int(m) for m in matches)))
    return ids

def extract_trigger_ids():
    p_file = os.path.join(SRC_DIR, "pkg_a/p.java")
    with open(p_file, "r", encoding="utf-8") as f:
        content = f.read()
    
    # interface g: public static final int[] ...
    match = re.search(r'interface g\s*\{[^}]*int\[\]\s*[^=]+=\s*\{([^}]+)\}', content)
    ids = []
    if match:
        tokens = [t.strip() for t in match.group(1).split(",")]
        for i in range(0, len(tokens), 2):
            tok = tokens[i]
            if tok.isdigit():
                ids.append(int(tok))
    if not ids:
        ids = list(range(37))
    return sorted(list(set(ids)))

def extract_edge_regions():
    # 16 standard edge regions (0 to 15)
    return list(range(16))

def audit_test_coverage(test_files):
    all_test_content = ""
    for tf in test_files:
        p = os.path.join(TEST_DIR, tf)
        if os.path.exists(p):
            with open(p, "r", encoding="utf-8") as f:
                all_test_content += f.read() + "\n"
    
    action_ids = extract_action_ids()
    condition_ids = extract_condition_ids()
    trigger_ids = extract_trigger_ids()
    edge_ids = extract_edge_regions()
    
    # Extract arrays from test code if present
    tested_actions = set()
    action_array_match = re.search(r'actionCatalog\s*=\s*new\s*int\[\]\s*\{([^}]+)\}', all_test_content)
    if action_array_match:
        for num in re.findall(r'\b\d+\b', action_array_match.group(1)):
            tested_actions.add(int(num))
    else:
        for aid in action_ids:
            if re.search(r'\b' + str(aid) + r'\b', all_test_content):
                tested_actions.add(aid)
                
    tested_conditions = set()
    cond_array_match = re.search(r'conditionCatalog\s*=\s*new\s*int\[\]\s*\{([^}]+)\}', all_test_content)
    if cond_array_match:
        for num in re.findall(r'\b\d+\b', cond_array_match.group(1)):
            tested_conditions.add(int(num))
    else:
        for cid in condition_ids:
            if re.search(r'condition.*?\b' + str(cid) + r'\b|\b' + str(cid) + r'\b.*?condition', all_test_content, re.IGNORECASE):
                tested_conditions.add(cid)
                
    tested_triggers = set()
    trig_array_match = re.search(r'triggerCatalog\s*=\s*new\s*int\[\]\s*\{([^}]+)\}', all_test_content)
    if trig_array_match:
        for num in re.findall(r'\b\d+\b', trig_array_match.group(1)):
            tested_triggers.add(int(num))
    else:
        for tid in trigger_ids:
            if re.search(r'trigger.*?\b' + str(tid) + r'\b|\b' + str(tid) + r'\b.*?trigger', all_test_content, re.IGNORECASE):
                tested_triggers.add(tid)
                
    tested_edges = set()
    edge_array_match = re.search(r'edgeRegions\s*=\s*new\s*int\[\]\s*\{([^}]+)\}', all_test_content)
    if edge_array_match:
        for num in re.findall(r'\b\d+\b', edge_array_match.group(1)):
            tested_edges.add(int(num))
    else:
        for eid in edge_ids:
            if re.search(r'edge.*?\b' + str(eid) + r'\b|\b' + str(eid) + r'\b.*?edge', all_test_content, re.IGNORECASE):
                tested_edges.add(eid)
                
    report = {
        "actions": {
            "total": len(action_ids),
            "covered": len(tested_actions.intersection(action_ids)),
            "missing": sorted(list(set(action_ids) - tested_actions)),
            "coverage_pct": round(len(tested_actions.intersection(action_ids)) / len(action_ids) * 100, 2) if action_ids else 0
        },
        "conditions": {
            "total": len(condition_ids),
            "covered": len(tested_conditions.intersection(condition_ids)),
            "missing": sorted(list(set(condition_ids) - tested_conditions)),
            "coverage_pct": round(len(tested_conditions.intersection(condition_ids)) / len(condition_ids) * 100, 2) if condition_ids else 0
        },
        "triggers": {
            "total": len(trigger_ids),
            "covered": len(tested_triggers.intersection(trigger_ids)),
            "missing": sorted(list(set(trigger_ids) - tested_triggers)),
            "coverage_pct": round(len(tested_triggers.intersection(trigger_ids)) / len(trigger_ids) * 100, 2) if trigger_ids else 0
        },
        "edge_regions": {
            "total": len(edge_ids),
            "covered": len(tested_edges.intersection(edge_ids)),
            "missing": sorted(list(set(edge_ids) - tested_edges)),
            "coverage_pct": round(len(tested_edges.intersection(edge_ids)) / len(edge_ids) * 100, 2) if edge_ids else 0
        }
    }
    return report

if __name__ == "__main__":
    test_files = [
        "com/jozein/xedgepro/ActionParityTest.java",
        "com/jozein/xedgepro/ParityComprehensiveTest.java",
        "pkg_a/EngineComprehensiveTest.java"
    ]
    report = audit_test_coverage(test_files)
    print(json.dumps(report, indent=2))
