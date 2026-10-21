package pkg_f;

/* JADX INFO: loaded from: classes.dex */
public abstract class i {

    private static class a extends i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final i f438a;

        a(i iVar) {
            this.f438a = iVar;
        }

        @Override // pkg_f.i
        public int a(f fVar) {
            return this.f438a.a(fVar) ^ (-1);
        }

        @Override // pkg_f.i
        public Integer b() {
            Integer numB = this.f438a.b();
            if (numB != null) {
                return Integer.valueOf(numB.intValue() ^ (-1));
            }
            return null;
        }

        public String toString() {
            return '~' + this.f438a.toString();
        }
    }

    private static class b extends i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final i f439a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final char f440b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final i f441c;

        b(i iVar, char c2, i iVar2) {
            this.f439a = iVar;
            this.f440b = c2;
            this.f441c = iVar2;
        }

        private int g(int i, int i2) {
            char c2 = this.f440b;
            if (c2 == '#') {
                return i >> i2;
            }
            if (c2 == '-') {
                return i - i2;
            }
            if (c2 == '/') {
                return i / i2;
            }
            if (c2 == '@') {
                return i << i2;
            }
            if (c2 == '^') {
                return i ^ i2;
            }
            if (c2 == '|') {
                return i | i2;
            }
            if (c2 == '%') {
                return i % i2;
            }
            if (c2 == '&') {
                return i & i2;
            }
            if (c2 == '*') {
                return i * i2;
            }
            if (c2 == '+') {
                return i + i2;
            }
            throw new RuntimeException("Invalid operator: " + this.f440b);
        }

        @Override // pkg_f.i
        public int a(f fVar) {
            return g(this.f439a.a(fVar), this.f441c.a(fVar));
        }

        @Override // pkg_f.i
        public Integer b() {
            Integer numB;
            Integer numB2 = this.f439a.b();
            if (numB2 == null || (numB = this.f441c.b()) == null) {
                return null;
            }
            return Integer.valueOf(g(numB2.intValue(), numB.intValue()));
        }

        public String toString() {
            String string;
            char c2 = this.f440b;
            if (c2 == '@') {
                string = "<<";
            } else {
                string = c2 == '#' ? ">>" : Character.toString(c2);
            }
            return '(' + this.f439a.toString() + ' ' + string + ' ' + this.f441c.toString() + ')';
        }
    }

    private static class c extends i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f442a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final char f443b;

        c(String str, char c2) {
            this.f442a = str;
            this.f443b = c2;
        }

        @Override // pkg_f.i
        public int a(f fVar) {
            t tVarB = fVar.b(this.f442a);
            if (tVarB == null) {
                throw new RuntimeException("Variable not exists: " + this.f442a);
            }
            char c2 = this.f443b;
            if (c2 == '\"') {
                int i = tVarB.f485a + 1;
                tVarB.f485a = i;
                return i;
            }
            if (c2 == '\'') {
                int i2 = tVarB.f485a;
                tVarB.f485a = i2 + 1;
                return i2;
            }
            if (c2 == ',') {
                int i3 = tVarB.f485a;
                tVarB.f485a = i3 - 1;
                return i3;
            }
            if (c2 == ';') {
                int i4 = tVarB.f485a - 1;
                tVarB.f485a = i4;
                return i4;
            }
            throw new RuntimeException("Invalid operator: " + this.f443b);
        }

        @Override // pkg_f.i
        public Integer b() {
            return null;
        }

        public String toString() {
            StringBuilder sb;
            char c2 = this.f443b;
            String str = "++";
            if (c2 != '\"') {
                if (c2 != '\'') {
                    str = "--";
                    if (c2 == ',') {
                        sb = new StringBuilder();
                    } else {
                        if (c2 != ';') {
                            throw new RuntimeException("Invalid operator: " + this.f443b);
                        }
                        sb = new StringBuilder();
                    }
                } else {
                    sb = new StringBuilder();
                }
                sb.append(this.f442a);
                sb.append(str);
                return sb.toString();
            }
            sb = new StringBuilder();
            sb.append(str);
            sb.append(this.f442a);
            return sb.toString();
        }
    }

    private static class d extends i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f444a;

        d(int i) {
            this.f444a = i;
        }

        @Override // pkg_f.i
        public int a(f fVar) {
            return this.f444a;
        }

        @Override // pkg_f.i
        public Integer b() {
            return Integer.valueOf(this.f444a);
        }

        public String toString() {
            return Integer.toString(this.f444a);
        }
    }

    private static class e extends i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final i f445a;

        e(i iVar) {
            this.f445a = iVar;
        }

        @Override // pkg_f.i
        public int a(f fVar) {
            return -this.f445a.a(fVar);
        }

        @Override // pkg_f.i
        public Integer b() {
            Integer numB = this.f445a.b();
            if (numB != null) {
                return Integer.valueOf(-numB.intValue());
            }
            return null;
        }

        public String toString() {
            return '-' + this.f445a.toString();
        }
    }

    public interface f {
        int a(String str);

        t b(String str);
    }

    private static class g extends i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f446a;

        g(String str) {
            this.f446a = str;
        }

        @Override // pkg_f.i
        public int a(f fVar) {
            return fVar.a(this.f446a);
        }

        @Override // pkg_f.i
        public Integer b() {
            return null;
        }

        public String toString() {
            return this.f446a;
        }
    }

    /* JADX WARN: Code duplicated, block: B:33:0x004a  */
    /* JADX WARN: Code duplicated, block: B:34:0x004c A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:36:0x0050  */
    /* JADX WARN: Code duplicated, block: B:41:0x0064  */
    private static int c(String str, int i, int i2) {
        int i3 = 0;
        int i4 = -1;
        int i5 = -1;
        int i6 = -1;
        int i7 = -1;
        int i8 = -1;
        for (int i9 = i2 - 1; i9 > i; i9--) {
            char cCharAt = str.charAt(i9);
            if (cCharAt == '#') {
                if (i3 == 0 && i5 < 0) {
                    i5 = i9;
                }
            } else if (cCharAt == '-') {
                if (i3 == 0 && i8 < 0 && "|^&<>+-*/%@#\"';,".indexOf(str.charAt(i9 - 1)) == -1) {
                    i8 = i9;
                }
            } else if (cCharAt == '/') {
                if (i3 == 0 && i6 < 0) {
                    i6 = i9;
                }
            } else if (cCharAt != '@') {
                if (cCharAt != '^') {
                    if (cCharAt != '|') {
                        if (cCharAt == '%') {
                            if (i3 == 0) {
                                i6 = i9;
                            }
                        } else if (cCharAt != '&') {
                            switch (cCharAt) {
                                case '(':
                                    i3--;
                                    break;
                                case ')':
                                    i3++;
                                    break;
                                case '*':
                                    if (i3 == 0) {
                                        i6 = i9;
                                    }
                                    break;
                                case '+':
                                    if (i3 == 0) {
                                        i8 = i9;
                                    }
                                    break;
                            }
                        } else if (i3 == 0 && i7 < 0) {
                            i7 = i9;
                        }
                    } else if (i3 == 0) {
                        return i9;
                    }
                } else if (i3 == 0 && i4 < 0) {
                    i4 = i9;
                }
            } else if (i3 == 0) {
                i5 = i9;
            }
            if (i3 < 0) {
                throw new RuntimeException("Invalid expression.");
            }
        }
        if (str.charAt(i) == '(') {
            i3--;
        }
        if (i3 != 0) {
            throw new RuntimeException("Invalid expression.");
        }
        if (i4 > 0) {
            return i4;
        }
        if (i7 > 0) {
            return i7;
        }
        if (i5 > 0) {
            return i5;
        }
        return i8 > 0 ? i8 : i6;
    }

    public static i d(String str) {
        if (str == null || str.length() == 0) {
            throw new RuntimeException("Empty expression.");
        }
        String strF = f(str);
        return e(strF, 0, strF.length());
    }

    private static i e(String str, int i, int i2) {
        int iC = c(str, i, i2);
        if (iC != -1) {
            i iVarE = e(str, i, iC);
            char cCharAt = str.charAt(iC);
            i iVarE2 = e(str, iC + 1, i2);
            Integer numB = new b(iVarE, cCharAt, iVarE2).b();
            return numB != null ? new d(numB.intValue()) : new b(iVarE, cCharAt, iVarE2);
        }
        char cCharAt2 = str.charAt(i);
        if (cCharAt2 != '\"' && cCharAt2 != ';') {
            if (cCharAt2 == '~') {
                i iVarE3 = e(str, i + 1, i2);
                Integer numB2 = iVarE3.b();
                return numB2 != null ? new d(numB2.intValue() ^ (-1)) : new a(iVarE3);
            }
            if (cCharAt2 != '\'') {
                if (cCharAt2 != '(') {
                    switch (cCharAt2) {
                        case '+':
                            return e(str, i + 1, i2);
                        case '-':
                            i iVarE4 = e(str, i + 1, i2);
                            Integer numB3 = iVarE4.b();
                            return numB3 != null ? new d(-numB3.intValue()) : new e(iVarE4);
                    }
                }
                int i3 = i2 - 1;
                if (str.charAt(i3) == ')') {
                    return e(str, i + 1, i3);
                }
                return ((cCharAt2 < 'a' || cCharAt2 > 'z') && !((cCharAt2 >= 'A' && cCharAt2 <= 'Z') || cCharAt2 == '_' || cCharAt2 == '$')) ? new d(Integer.parseInt(str.substring(i, i2))) : new g(str.substring(i, i2));
            }
        }
        int i4 = i + 1;
        char cCharAt3 = str.charAt(i4);
        if ((cCharAt3 < 'a' || cCharAt3 > 'z') && !((cCharAt3 >= 'A' && cCharAt3 <= 'Z') || cCharAt3 == '_' || cCharAt3 == '$')) {
            throw new RuntimeException("Invalid expression.");
        }
        return new c(str.substring(i4, i2), cCharAt2);
    }

    /* JADX WARN: Code duplicated, block: B:62:0x00a9  */
    private static String f(String str) {
        int i;
        char c2;
        char c3;
        char c4 = 0;
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        int i2 = 0;
        int length2 = -1;
        boolean z = false;
        while (i2 < length) {
            char cCharAt = str.charAt(i2);
            if (cCharAt != ' ') {
                if (cCharAt == '-') {
                    i = i2 + 1;
                    if (str.charAt(i) == '-') {
                        if (z) {
                            throw new RuntimeException("Invalid expression.");
                        }
                        if (length2 < 0) {
                            c3 = ';';
                            sb.append(c3);
                            i2 = i;
                            z = true;
                        } else {
                            c2 = ',';
                            sb.insert(length2, c2);
                            i2 = i;
                            z = true;
                        }
                    }
                    z = false;
                    sb.append(cCharAt);
                } else if (cCharAt == '/') {
                    length2 = -1;
                    z = false;
                    sb.append(cCharAt);
                } else {
                    if (cCharAt == '<') {
                        i2++;
                        if (str.charAt(i2) != '<') {
                            throw new RuntimeException("Invalid expression.");
                        }
                        c4 = '@';
                    } else if (cCharAt != '>') {
                        if (cCharAt == '^' || cCharAt == '|' || cCharAt == '%' || cCharAt == '&' || cCharAt == '*') {
                            length2 = -1;
                        } else {
                            if (cCharAt == '+') {
                                i = i2 + 1;
                                if (str.charAt(i) == '+') {
                                    if (z) {
                                        throw new RuntimeException("Invalid expression.");
                                    }
                                    if (length2 < 0) {
                                        c3 = '\"';
                                        sb.append(c3);
                                        i2 = i;
                                        z = true;
                                    } else {
                                        c2 = '\'';
                                        sb.insert(length2, c2);
                                        i2 = i;
                                        z = true;
                                    }
                                }
                            } else if (length2 < 0 && ((cCharAt >= 'a' && cCharAt <= 'z') || ((cCharAt >= 'A' && cCharAt <= 'Z') || cCharAt == '_' || cCharAt == '$'))) {
                                length2 = sb.length();
                            }
                            sb.append(cCharAt);
                        }
                        z = false;
                        sb.append(cCharAt);
                    } else {
                        i2++;
                        if (str.charAt(i2) != '>') {
                            throw new RuntimeException("Invalid expression.");
                        }
                        c4 = '#';
                    }
                    sb.append(c4);
                    length2 = -1;
                    z = false;
                }
            }
            i2++;
        }
        return sb.length() != length ? sb.toString() : str;
    }

    public abstract int a(f fVar);

    public abstract Integer b();
}
