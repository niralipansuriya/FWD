package com.swipefwd.data.source;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00a8\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\bf\u0018\u00002\u00020\u0001J-\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ-\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ-\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ8\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u0014H&J-\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ-\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ-\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ-\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ5\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001fJA\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u0014H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"J-\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJQ\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0006\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010+J%\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0\u00040\u00032\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010.J%\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101J%\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101J-\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002040\u00040\u00032\u0006\u00105\u001a\u00020\u00072\u0006\u00106\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ%\u00107\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002080\u00040\u00032\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010.J%\u00109\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020:0\u00040\u00032\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010.JI\u0010;\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020<0\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=J%\u0010>\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020?0\u00040\u00032\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010.J5\u0010@\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020A0\u00040\u00032\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010B\u001a\u00020\u001e2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010CJ%\u0010D\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020E0\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101J%\u0010F\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020G0\u00040\u00032\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010.JU\u0010H\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020I0\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010J\u001a\u00020\u00072\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020L2\u0006\u0010N\u001a\u00020L2\u0006\u0010O\u001a\u00020L2\u0006\u0010P\u001a\u00020LH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010QJI\u0010R\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020S0\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=J%\u0010T\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020U0\u00040\u00032\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010.J%\u0010V\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020W0\u00040\u00032\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010.J%\u0010X\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Y0\u00040\u00032\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010.J-\u0010Z\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020[0\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ-\u0010\\\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020]0\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010^\u001a\u00020\u001eH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010_J-\u0010`\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020a0\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010^\u001a\u00020\u001eH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010_J-\u0010b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020c0\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010^\u001a\u00020\u001eH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010_J%\u0010d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020e0\u00040\u00032\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010.J-\u0010f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020g0\u00040\u00032\u0006\u0010h\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ-\u0010i\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020j0\u00040\u00032\u0006\u0010\u001d\u001a\u00020k2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010lJ%\u0010m\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020n0\u00040\u00032\u0006\u0010o\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101J%\u0010p\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020q0\u00040\u00032\u0006\u0010r\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101JI\u0010s\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020t0\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=J-\u0010u\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ-\u0010v\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020w0\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u001f\u0010x\u001a\b\u0012\u0004\u0012\u00020y0\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101J\u001f\u0010z\u001a\b\u0012\u0004\u0012\u00020{0\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101J\u001f\u0010|\u001a\b\u0012\u0004\u0012\u00020}0\u00042\u0006\u0010o\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101J%\u0010~\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u007f0\u00040\u00032\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010.J/\u0010\u0080\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0081\u00010\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ0\u0010\u0082\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0083\u00010\u00040\u00032\u0006\u0010^\u001a\u00020\u001e2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u0084\u0001J.\u0010\u0085\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ.\u0010\u0086\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010^\u001a\u00020\u001eH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010_J/\u0010\u0087\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0088\u00010\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ.\u0010\u0089\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\'\u0010\u008a\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u008b\u00010\u00040\u00032\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010.JJ\u0010\u008c\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=JJ\u0010\u008d\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=J9\u0010\u008e\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u0014H&J&\u0010\u008f\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u007f0\u00040\u00032\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010.J/\u0010\u0090\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0088\u00010\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ/\u0010\u0091\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0092\u00010\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ.\u0010\u0093\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJC\u0010\u0094\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0095\u00010\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u0014H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"JK\u0010\u0096\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0097\u00010\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=J/\u0010\u0098\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0099\u00010\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJK\u0010\u0098\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u009a\u00010\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=J.\u0010\u009b\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ9\u0010\u009c\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u009d\u00010\u00040\u00032\u0007\u0010\u009e\u0001\u001a\u00020(2\u0006\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u009f\u0001J/\u0010\u00a0\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00a1\u00010\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJK\u0010\u00a2\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0095\u00010\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=J0\u0010\u00a3\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00a4\u00010\u00040\u00032\u0006\u0010)\u001a\u00020*2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u00a5\u0001J.\u0010\u00a6\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJK\u0010\u00a7\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00a8\u00010\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=JK\u0010\u00a9\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00aa\u00010\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=JK\u0010\u00ab\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00ac\u00010\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=JK\u0010\u00ad\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00ae\u00010\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=J/\u0010\u00af\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00b0\u00010\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJJ\u0010\u00b1\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020S0\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=J/\u0010\u00b2\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00b3\u00010\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ0\u0010\u00b4\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00b5\u00010\u00040\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u00b6\u0001J2\u0010\u00b7\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0083\u00010\u00040\u00032\b\u0010^\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u00b8\u0001J0\u0010\u00b9\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00ba\u00010\u00040\u00032\u0006\u0010^\u001a\u00020\u001e2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u0084\u0001J8\u0010\u00bb\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00b5\u00010\u00040\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u001eH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u00bc\u0001JK\u0010\u00bd\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00ac\u00010\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=J.\u0010\u00be\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\'\u0010\u00bf\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00c0\u00010\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101JD\u0010\u00bf\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00c1\u00010\u00040\u00032#\u0010\u00c2\u0001\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u0014H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"JK\u0010\u00c3\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00c4\u00010\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=J/\u0010\u00c5\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00c6\u00010\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJJ\u0010\u00c7\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020S0\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=J.\u0010\u00c8\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ(\u0010\u00c9\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00ca\u00010\u00040\u00032\u0006\u0010)\u001a\u00020*H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u00cb\u0001JJ\u0010\u00cc\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=JN\u0010\u00cd\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00ce\u00010\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\b\u0010\u00cf\u0001\u001a\u00030\u00d0\u0001H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u00d1\u0001J&\u0010\u00d2\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101JK\u0010\u00d3\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00d4\u00010\u00040\u00032\"\u0010\u0006\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=J\'\u0010\u00d5\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00d6\u00010\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101JJ\u0010\u00d7\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=J6\u0010\u00d8\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010^\u001a\u00020\u001e2\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001fJW\u0010\u00d9\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00da\u00010\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0007\u0010\u00db\u0001\u001a\u00020\u00072\b\u0010\u00cf\u0001\u001a\u00030\u00d0\u0001H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u00dc\u0001J:\u0010\u00dd\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0007\u0010\u00de\u0001\u001a\u00020\u00072\b\u0010\u00df\u0001\u001a\u00030\u00d0\u0001H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u00e0\u0001JK\u0010\u00e1\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00d4\u00010\u00040\u00032\"\u0010\u0006\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=J.\u0010\u00e2\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ6\u0010\u00e3\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010^\u001a\u00020\u001e2\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001fJ6\u0010\u00e4\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010^\u001a\u00020\u001e2\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001fJJ\u0010\u00e5\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020S0\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=JJ\u0010\u00e6\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020S0\u00040\u00032\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00142\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u00e7\u0001"}, d2 = {"Lcom/swipefwd/data/source/AppDataSource;", "", "addActivePendingRequestApi", "Lkotlinx/coroutines/flow/Flow;", "Lretrofit2/Response;", "Lcom/swipefwd/data/models/CommonResponseModel;", "token", "", "jsonObject", "Lcom/google/gson/JsonObject;", "(Ljava/lang/String;Lcom/google/gson/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "astrologySignListApi", "Lcom/swipefwd/data/models/AstroSignListModel;", "page", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "boosterPurchaseApi", "callWebservice", "Lcom/swipefwd/data/models/RefreshTokenModel;", "headers", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "castListApi", "Lcom/swipefwd/data/models/CastListModel;", "changeUserTypeApi", "childrenListApi", "Lcom/swipefwd/data/models/ChildrenListModel;", "covidVaccineListApi", "Lcom/swipefwd/data/models/CovidVaccineListModel;", "deleteAccount", "userId", "", "(Ljava/lang/String;ILcom/google/gson/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteUserAccount", "Lcom/swipefwd/data/models/DeleteAccountModel;", "(Ljava/util/HashMap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "educationLevelListApi", "Lcom/swipefwd/data/models/EducationLevelListModel;", "gestureProfileVerification", "Lcom/swipefwd/data/models/GestureVerificationProfileModel;", "requestBodyGender", "Lokhttp3/RequestBody;", "photo", "Lokhttp3/MultipartBody$Part;", "(Ljava/util/HashMap;Lokhttp3/RequestBody;Lokhttp3/MultipartBody$Part;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAccessTokenApi", "Lcom/swipefwd/data/models/walletModels/GetAccessTokenModel;", "(Lcom/google/gson/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActivePendingRequestApi", "Lcom/swipefwd/data/models/ActiveExpireRequestListModel;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActivityPendingRequestApi", "getAddressFromLatLong", "Lcom/swipefwd/data/models/walletModels/GoogleAddressModel;", "key", "latlng", "getAstrologicalSign", "Lcom/swipefwd/data/models/AstrologicalModel;", "getChildrenApi", "Lcom/swipefwd/data/models/ChildrenModel;", "getDateList", "Lcom/swipefwd/data/models/DateGetModel;", "(Ljava/util/HashMap;Lcom/google/gson/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getEducationApi", "Lcom/swipefwd/data/models/EducationDetailModel;", "getFacebookListIds", "Lcom/swipefwd/data/models/FacebookMutualFriendsListModel;", "daterId", "(IILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getInactivity", "Lcom/swipefwd/data/models/InactivityModel;", "getLanguageApi", "Lcom/swipefwd/data/models/LanguageDataModel;", "getOperatorByIso", "Lcom/swipefwd/data/models/walletModels/GetOperatorByIsoModel;", "isoCode", "includeBundles", "", "includeData", "includePin", "suggestedAmounts", "suggestedAmountsMap", "(Ljava/lang/String;Ljava/lang/String;ZZZZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOtpApi", "LOTPModel;", "getRelationshipApi", "Lcom/swipefwd/data/models/RelationshipModel;", "getReligionApi", "Lcom/swipefwd/data/models/ReligionModel;", "getSmokingApi", "Lcom/swipefwd/data/models/SmokingDataModel;", "getUserImagesApi", "Lcom/swipefwd/data/models/UserImagesResponseModel;", "getUserPreferenceApi", "Lcom/swipefwd/data/models/UserPreferenceResponseModel;", "id", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserProfileApi", "Lcom/swipefwd/data/models/UserProfileResponseModel;", "getUserSettingsApi", "Lcom/swipefwd/data/models/UserSettingsResponseModel;", "getVaccinationApi", "Lcom/swipefwd/data/models/VaccinationModel;", "instagramMediaApi", "Lcom/swipefwd/data/models/InstagramMediaModel;", "mediaId", "instagramProfileAPi", "Lcom/swipefwd/data/models/InstagramProfileModel;", "", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "instagramTokenApi", "Lcom/swipefwd/data/models/InstagramTokenModel;", "code", "instituteListApi", "Lcom/swipefwd/data/models/InstitutesModel;", "url", "inviteConnector", "Lcom/swipefwd/data/models/InviteConnectorResponseModel;", "inviteContactApi", "languageListApi", "Lcom/swipefwd/data/models/LanguageListModel;", "linkedInEmailApi", "Lcom/swipefwd/data/models/LinkedInEmailModel;", "linkedInProfileApi", "Lcom/swipefwd/data/models/LinkedInProfileModel;", "linkedInTokenApi", "Lcom/swipefwd/data/models/LinkedInTokenModel;", "loginUserApi", "Lcom/swipefwd/data/models/LoginResponseModel;", "makeRecharge", "Lcom/swipefwd/data/models/walletModels/MakeRechargeModel;", "matchesListApi", "Lcom/swipefwd/data/models/SwipingProfileMatchesModel;", "(ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "planPurchaseApi", "popupShowApi", "preferenceRelationshipListApi", "Lcom/swipefwd/data/models/RelationshipListModel;", "recoverAccountApi", "recoverAccountEmailApi", "Lcom/swipefwd/data/models/RecoverAccountModel;", "recoverEmailAddress", "recoverEmailApi", "refreshToken", "registerUserApi", "relationshipListApi", "religionListApi", "Lcom/swipefwd/data/models/ReligionListModel;", "removeTribeDaterApi", "resendOtp", "Lcom/swipefwd/data/models/ResendOtp;", "sendBasicProfileData", "LBasicProfileDetails;", "sendContactApi", "Lcom/swipefwd/data/models/FwdContactListResponseModel;", "Lcom/swipefwd/data/models/ContactListResponseModel;", "sendFacebookIdApi", "sendImageGestureVerification", "Lcom/swipefwd/data/models/GestureVerificationModel;", "requestBodyUserId", "(Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/MultipartBody$Part;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendInstaImagesApi", "Lcom/swipefwd/data/models/InstagramUploadResponseModel;", "sendOtpEmail", "sendProfileVerification", "Lcom/swipefwd/data/models/ImageModerationResponseModel;", "(Lokhttp3/MultipartBody$Part;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendReceipt", "setDetailedProfile", "Lcom/swipefwd/data/models/DetailedProfileModel;", "setEmailUserApi", "Lcom/swipefwd/data/models/SetEmailModel;", "setLocation", "Lcom/swipefwd/data/models/LocationModel;", "setUserTypeApi", "Lcom/swipefwd/data/models/UserTypeModel;", "smokingListApi", "Lcom/swipefwd/data/models/SmokingListModel;", "socialLogin", "suggestionProfileApi", "Lcom/swipefwd/data/models/InviteModel;", "swipingLeftRightApi", "Lcom/swipefwd/data/models/SwipeLeftRightResponseModel;", "(Lcom/google/gson/JsonObject;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "swipingMatchesApi", "(Ljava/lang/Integer;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "swipingProfileApi", "Lcom/swipefwd/data/models/SwipingProfileModel;", "swipingRewindApi", "(Lcom/google/gson/JsonObject;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "travelLocationApi", "tribeConnectionApi", "tribeConnectionListApi", "Lcom/swipefwd/data/models/DaterConnectionResponseModel;", "Lcom/swipefwd/data/models/TribeDaterConnectionsResponseModel;", "header", "updateDateList", "Lcom/swipefwd/data/models/UpdateDateModel;", "updateDeviceToken", "Lcom/swipefwd/data/models/DeviceTokenResponse;", "updateMobile", "updateTravelLocation", "uploadChatImage", "Lcom/swipefwd/xmpp/ChatImageModel;", "(Lokhttp3/MultipartBody$Part;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "userAdvancePreferenceSubmitApi", "userBasicProfileSet", "LBasicProfileUpload;", "file", "Ljava/io/File;", "(Ljava/util/HashMap;Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "userLogoutApi", "userPictureDeleteAsync", "LProfilePhotosModel$DataProfile$UserPhotos;", "userPlansApi", "Lcom/swipefwd/data/models/SettingSubscriptionModel;", "userPreferenceSubmitApi", "userPreferenceUpdateApi", "userProfilePhotosUpload", "LProfilePhotosModel;", "position", "(Ljava/util/HashMap;Ljava/lang/String;Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "userProfilePictureApi", "isProfile", "imageFile", "(Ljava/lang/String;Ljava/lang/String;Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "userProfilePictureDeleteApi", "userProfileSubmitApi", "userProfileUpdateApi", "userSettingsUpdateApi", "verifyOtpApi", "verifyOtpEmail", "app_debug"})
public abstract interface AppDataSource {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object linkedInTokenApi(@org.jetbrains.annotations.NotNull()
    java.lang.String code, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.swipefwd.data.models.LinkedInTokenModel>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object linkedInProfileApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.swipefwd.data.models.LinkedInProfileModel>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object linkedInEmailApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.swipefwd.data.models.LinkedInEmailModel>> continuation);
    
    /**
     * #######################################################################
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object loginUserApi(@org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.LoginResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getEducationApi(@org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.EducationDetailModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLanguageApi(@org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.LanguageDataModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAstrologicalSign(@org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.AstrologicalModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteUserAccount(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.DeleteAccountModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRelationshipApi(@org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.RelationshipModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getChildrenApi(@org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.ChildrenModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getVaccinationApi(@org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.VaccinationModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getReligionApi(@org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.ReligionModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSmokingApi(@org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.SmokingDataModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object recoverAccountEmailApi(@org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.RecoverAccountModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object registerUserApi(@org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.LoginResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getOtpApi(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<OTPModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDateList(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.DateGetModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateDateList(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.UpdateDateModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setEmailUserApi(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.SetEmailModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object verifyOtpEmail(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<OTPModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendOtpEmail(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.ResendOtp>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setUserTypeApi(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.UserTypeModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object resendOtp(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.ResendOtp>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendBasicProfileData(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<BasicProfileDetails>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setDetailedProfile(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.DetailedProfileModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateMobile(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<OTPModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object socialLogin(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<OTPModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object verifyOtpApi(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<OTPModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setLocation(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.LocationModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object travelLocationApi(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.LocationModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object tribeConnectionListApi(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> header, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.TribeDaterConnectionsResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendContactApi(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.ContactListResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object inviteConnector(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.InviteConnectorResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object languageListApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.LanguageListModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object educationLevelListApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.EducationLevelListModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object astrologySignListApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.AstroSignListModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object castListApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CastListModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object childrenListApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.ChildrenListModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object religionListApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.ReligionListModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object smokingListApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.SmokingListModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object relationshipListApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.RelationshipListModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object covidVaccineListApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CovidVaccineListModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object userProfileSubmitApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAccount(@org.jetbrains.annotations.NotNull()
    java.lang.String token, int userId, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object userProfilePictureApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String isProfile, @org.jetbrains.annotations.NotNull()
    java.io.File imageFile, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object userProfilePictureDeleteApi(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> token, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<ProfilePhotosModel.DataProfile.UserPhotos>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object userPictureDeleteAsync(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> token, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<ProfilePhotosModel.DataProfile.UserPhotos>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object instituteListApi(@org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.InstitutesModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object preferenceRelationshipListApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.RelationshipListModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object userPreferenceSubmitApi(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object userAdvancePreferenceSubmitApi(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object instagramTokenApi(@org.jetbrains.annotations.NotNull()
    java.lang.String code, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.InstagramTokenModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object instagramProfileAPi(long userId, @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.InstagramProfileModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object instagramMediaApi(@org.jetbrains.annotations.NotNull()
    java.lang.String mediaId, @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.InstagramMediaModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object userProfileUpdateApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, int id, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object userPreferenceUpdateApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, int id, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object recoverEmailApi(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object userBasicProfileSet(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    java.io.File file, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<BasicProfileUpload>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object userProfilePhotosUpload(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    java.lang.String position, @org.jetbrains.annotations.NotNull()
    java.io.File file, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<ProfilePhotosModel>>> continuation);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.RefreshTokenModel>> refreshToken(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.RefreshTokenModel>> callWebservice(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object recoverEmailAddress(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object recoverAccountApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendContactApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.FwdContactListResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object inviteContactApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object tribeConnectionApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object tribeConnectionListApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.DaterConnectionResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object matchesListApi(int id, @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.SwipingProfileMatchesModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object changeUserTypeApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object swipingMatchesApi(@org.jetbrains.annotations.Nullable()
    java.lang.Integer id, @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.SwipingProfileMatchesModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object swipingProfileApi(int id, @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.SwipingProfileModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object swipingLeftRightApi(@org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.SwipeLeftRightResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object swipingRewindApi(@org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    java.lang.String token, int userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.SwipeLeftRightResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserProfileApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.UserProfileResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserImagesApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.UserImagesResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserPreferenceApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.UserPreferenceResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object userSettingsUpdateApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, int id, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserSettingsApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.UserSettingsResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendInstaImagesApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.InstagramUploadResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateDeviceToken(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.DeviceTokenResponse>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object removeTribeDaterApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object suggestionProfileApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.InviteModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object userPlansApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.SettingSubscriptionModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object planPurchaseApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object popupShowApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object boosterPurchaseApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object userLogoutApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getActivePendingRequestApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.ActiveExpireRequestListModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addActivePendingRequestApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getActivityPendingRequestApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.ActiveExpireRequestListModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendFacebookIdApi(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>>> continuation);
    
    /**
     * Wallet API
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAccessTokenApi(@org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.walletModels.GetAccessTokenModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getOperatorByIso(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String isoCode, boolean includeBundles, boolean includeData, boolean includePin, boolean suggestedAmounts, boolean suggestedAmountsMap, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.walletModels.GetOperatorByIsoModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object makeRecharge(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.walletModels.MakeRechargeModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getFacebookListIds(int userId, int daterId, @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.FacebookMutualFriendsListModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendProfileVerification(@org.jetbrains.annotations.NotNull()
    okhttp3.MultipartBody.Part photo, @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.ImageModerationResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object uploadChatImage(@org.jetbrains.annotations.NotNull()
    okhttp3.MultipartBody.Part photo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.xmpp.ChatImageModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendReceipt(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateTravelLocation(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendImageGestureVerification(@org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody requestBodyUserId, @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody requestBodyGender, @org.jetbrains.annotations.NotNull()
    okhttp3.MultipartBody.Part photo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.GestureVerificationModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object gestureProfileVerification(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody requestBodyGender, @org.jetbrains.annotations.NotNull()
    okhttp3.MultipartBody.Part photo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.GestureVerificationProfileModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAddressFromLatLong(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.String latlng, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.walletModels.GoogleAddressModel>>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getInactivity(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<retrofit2.Response<com.swipefwd.data.models.InactivityModel>>> continuation);
}