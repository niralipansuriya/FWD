package com.swipefwd.data.source.remote.api.services;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00c6\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J(\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\f\u001a\u00020\u0007H\'J(\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J.\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\b\b\u0001\u0010\u0011\u001a\u00020\u00072\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013H\'J(\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\f\u001a\u00020\u0007H\'J(\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J(\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\f\u001a\u00020\u0007H\'J(\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\f\u001a\u00020\u0007H\'J2\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\u001c\u001a\u00020\u001d2\b\b\u0001\u0010\b\u001a\u00020\tH\'J*\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013H\'J(\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\f\u001a\u00020\u0007H\'J@\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\b\b\u0001\u0010$\u001a\u00020%2\n\b\u0003\u0010&\u001a\u0004\u0018\u00010\'H\'J\u001e\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\'J\u001e\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\'J(\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0\u00040\u00032\b\b\u0001\u0010-\u001a\u00020\u00072\b\b\u0001\u0010.\u001a\u00020\u0007H\'J\u001e\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u00040\u00032\b\b\u0001\u0010\b\u001a\u00020\tH\'J\u001e\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002020\u00040\u00032\b\b\u0001\u0010\b\u001a\u00020\tH\'J4\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002040\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\b\b\u0001\u0010\b\u001a\u00020\tH\'J\u001e\u00105\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002060\u00040\u00032\b\b\u0001\u0010\b\u001a\u00020\tH\'J2\u00107\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002080\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\u001c\u001a\u00020\u001d2\b\b\u0001\u00109\u001a\u00020\u001dH\'J\u001e\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020;0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\'J\u001e\u0010<\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020=0\u00040\u00032\b\b\u0001\u0010\u0011\u001a\u00020\u0007H\'J\u001e\u0010>\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020?0\u00040\u00032\b\b\u0001\u0010\b\u001a\u00020\tH\'J(\u0010@\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020A0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010B\u001a\u00020\u001dH\'J4\u0010C\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020D0\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\b\b\u0001\u0010\b\u001a\u00020\tH\'J\u001e\u0010E\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020F0\u00040\u00032\b\b\u0001\u0010\b\u001a\u00020\tH\'J\u001e\u0010G\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020H0\u00040\u00032\b\b\u0001\u0010\b\u001a\u00020\tH\'J\u001e\u0010I\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020J0\u00040\u00032\b\b\u0001\u0010\b\u001a\u00020\tH\'J4\u0010K\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020D0\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\b\b\u0001\u0010\b\u001a\u00020\tH\'J\u001e\u0010L\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020M0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\'J(\u0010N\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020O0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010B\u001a\u00020\u001dH\'J(\u0010P\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010B\u001a\u00020\u001dH\'J(\u0010R\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020S0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\f\u001a\u00020\u0007H\'J(\u0010T\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020U0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010B\u001a\u00020\u001dH\'J\u001e\u0010V\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020W0\u00040\u00032\b\b\u0001\u0010\b\u001a\u00020\tH\'J*\u0010X\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Y0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\n\b\u0003\u0010&\u001a\u0004\u0018\u00010\'H\'J4\u0010Z\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020[0\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\b\b\u0001\u0010\b\u001a\u00020\tH\'J(\u0010\\\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J(\u0010]\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020^0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\f\u001a\u00020\u0007H\'J\u001e\u0010_\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020`0\u00040\u00032\b\b\u0001\u0010\b\u001a\u00020\tH\'J(\u0010a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J(\u0010b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010B\u001a\u00020\u001dH\'J(\u0010c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020d0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\f\u001a\u00020\u0007H\'J(\u0010e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J\u001e\u0010f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020g0\u00040\u00032\b\b\u0001\u0010\b\u001a\u00020\tH\'J4\u0010h\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\b\b\u0001\u0010\b\u001a\u00020\tH\'J(\u0010i\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J*\u0010j\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013H\'J\u001e\u0010k\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020`0\u00040\u00032\b\b\u0001\u0010\b\u001a\u00020\tH\'J(\u0010l\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020d0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\f\u001a\u00020\u0007H\'J(\u0010m\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020n0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\f\u001a\u00020\u0007H\'J(\u0010o\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J*\u0010p\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020q0\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013H\'J4\u0010r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020s0\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\b\b\u0001\u0010\b\u001a\u00020\tH\'J(\u0010t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020u0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J4\u0010t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020v0\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\b\b\u0001\u0010\b\u001a\u00020\tH\'J(\u0010w\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J(\u0010x\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020y0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J4\u0010z\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020q0\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\b\b\u0001\u0010\b\u001a\u00020\tH\'J(\u0010{\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J4\u0010|\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020}0\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\b\b\u0001\u0010\b\u001a\u00020\tH\'J4\u0010~\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u007f0\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\b\b\u0001\u0010\b\u001a\u00020\tH\'J6\u0010\u0080\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0081\u00010\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\b\b\u0001\u0010\b\u001a\u00020\tH\'J6\u0010\u0082\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0083\u00010\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\b\b\u0001\u0010\b\u001a\u00020\tH\'J*\u0010\u0084\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0085\u00010\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\f\u001a\u00020\u0007H\'J7\u0010\u0086\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0087\u00010\u00040\u00032\t\b\u0001\u0010\u0088\u0001\u001a\u00020%2\b\b\u0001\u0010$\u001a\u00020%2\n\b\u0003\u0010&\u001a\u0004\u0018\u00010\'H\'J*\u0010\u0089\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u008a\u00010\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J*\u0010\u008b\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u008c\u00010\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J1\u0010\u008d\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020A0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\n\b\u0001\u0010B\u001a\u0004\u0018\u00010\u001dH\'\u00a2\u0006\u0003\u0010\u008e\u0001J*\u0010\u008f\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0090\u00010\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010B\u001a\u00020\u001dH\'J \u0010\u0091\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u008c\u00010\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\'J4\u0010\u0092\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u008c\u00010\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\t2\b\b\u0001\u0010B\u001a\u00020\u001dH\'J6\u0010\u0093\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0081\u00010\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\b\b\u0001\u0010\b\u001a\u00020\tH\'J)\u0010\u0094\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J \u0010\u0095\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0096\u00010\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\'J,\u0010\u0095\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0097\u00010\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0013H\'J6\u0010\u0098\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0099\u00010\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\b\b\u0001\u0010\b\u001a\u00020\tH\'J*\u0010\u009a\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u009b\u00010\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J5\u0010\u009c\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020D0\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\b\b\u0001\u0010\b\u001a\u00020\tH\'J)\u0010\u009d\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J\"\u0010\u009e\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u009f\u00010\u00040\u00032\n\b\u0003\u0010&\u001a\u0004\u0018\u00010\'H\'JD\u0010\u00a0\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00a1\u00010\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\t\b\u0001\u0010\u00a2\u0001\u001a\u00020%2\u000b\b\u0003\u0010\u00a3\u0001\u001a\u0004\u0018\u00010\'H\'J5\u0010\u00a4\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\b\b\u0001\u0010\b\u001a\u00020\tH\'J9\u0010\u00a5\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00a6\u00010\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\u000b\b\u0003\u0010\u00a3\u0001\u001a\u0004\u0018\u00010\'H\'J\u001f\u0010\u00a7\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\'J6\u0010\u00a8\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00a9\u00010\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\b\b\u0001\u0010\b\u001a\u00020\tH\'J)\u0010\u00aa\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J5\u0010\u00aa\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\b\b\u0001\u0010\b\u001a\u00020\tH\'J3\u0010\u00ab\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010B\u001a\u00020\u001d2\b\b\u0001\u0010\b\u001a\u00020\tH\'J7\u0010\u00ac\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\t\b\u0001\u0010\u00ad\u0001\u001a\u00020%2\u000b\b\u0003\u0010\u00ae\u0001\u001a\u0004\u0018\u00010\'H\'J6\u0010\u00af\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u00a9\u00010\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\b\b\u0001\u0010\b\u001a\u00020\tH\'J)\u0010\u00b0\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J3\u0010\u00b1\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010B\u001a\u00020\u001d2\b\b\u0001\u0010\b\u001a\u00020\tH\'J3\u0010\u00b2\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010B\u001a\u00020\u001d2\b\b\u0001\u0010\b\u001a\u00020\tH\'J5\u0010\u00b3\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020D0\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\b\b\u0001\u0010\b\u001a\u00020\tH\'J5\u0010\u00b4\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020D0\u00040\u00032\u0014\b\u0001\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00132\b\b\u0001\u0010\b\u001a\u00020\tH\'\u00a8\u0006\u00b5\u0001"}, d2 = {"Lcom/swipefwd/data/source/remote/api/services/ApiService;", "", "addActivePendingRequestAsync", "Lkotlinx/coroutines/Deferred;", "Lretrofit2/Response;", "Lcom/swipefwd/data/models/CommonResponseModel;", "Authorization", "", "jsonObject", "Lcom/google/gson/JsonObject;", "astrologySignListAsync", "Lcom/swipefwd/data/models/AstroSignListModel;", "page", "boosterPurchaseAsync", "callWebservice", "Lretrofit2/Call;", "Lcom/swipefwd/data/models/RefreshTokenModel;", "url", "headers", "", "castListAsync", "Lcom/swipefwd/data/models/CastListModel;", "changeUserTypeAsync", "childrenListAsync", "Lcom/swipefwd/data/models/ChildrenListModel;", "covidVaccineListAsync", "Lcom/swipefwd/data/models/CovidVaccineListModel;", "deleteAccountAsync", "userId", "", "deleteUserAccount", "Lcom/swipefwd/data/models/DeleteAccountModel;", "educationLevelListAsync", "Lcom/swipefwd/data/models/EducationLevelListModel;", "gestureProfileVerification", "Lcom/swipefwd/data/models/GestureVerificationProfileModel;", "requestBodyGender", "Lokhttp3/RequestBody;", "photo", "Lokhttp3/MultipartBody$Part;", "getActivePendingRequestAsync", "Lcom/swipefwd/data/models/ActiveExpireRequestListModel;", "getActivityPendingRequestAsync", "getAddressFromLatLngAsync", "Lcom/swipefwd/data/models/walletModels/GoogleAddressModel;", "key", "latlng", "getAstrologicalSign", "Lcom/swipefwd/data/models/AstrologicalModel;", "getChildren", "Lcom/swipefwd/data/models/ChildrenModel;", "getDateList", "Lcom/swipefwd/data/models/DateGetModel;", "getEducation", "Lcom/swipefwd/data/models/EducationDetailModel;", "getFacebookIdsAsync", "Lcom/swipefwd/data/models/FacebookMutualFriendsListModel;", "daterId", "getInactivtyRequestAsync", "Lcom/swipefwd/data/models/InactivityModel;", "getInstitutesAsync", "Lcom/swipefwd/data/models/InstitutesModel;", "getLanguageApi", "Lcom/swipefwd/data/models/LanguageDataModel;", "getMatchesListAsync", "Lcom/swipefwd/data/models/SwipingProfileMatchesModel;", "id", "getOtpAsync", "LOTPModel;", "getRelationship", "Lcom/swipefwd/data/models/RelationshipModel;", "getReligion", "Lcom/swipefwd/data/models/ReligionModel;", "getSmokingApi", "Lcom/swipefwd/data/models/SmokingDataModel;", "getSocialsignUp", "getUserPlansAsync", "Lcom/swipefwd/data/models/SettingSubscriptionModel;", "getUserPreferenceAsync", "Lcom/swipefwd/data/models/UserPreferenceResponseModel;", "getUserProfileAsync", "Lcom/swipefwd/data/models/UserProfileResponseModel;", "getUserProfileImagesAsync", "Lcom/swipefwd/data/models/UserImagesResponseModel;", "getUserSettingsAsync", "Lcom/swipefwd/data/models/UserSettingsResponseModel;", "getVaccination", "Lcom/swipefwd/data/models/VaccinationModel;", "imageModerationAsync", "Lcom/swipefwd/data/models/ImageModerationResponseModel;", "inviteConnector", "Lcom/swipefwd/data/models/InviteConnectorResponseModel;", "inviteContactAsync", "languageListAsync", "Lcom/swipefwd/data/models/LanguageListModel;", "loginUserAsync", "Lcom/swipefwd/data/models/LoginResponseModel;", "planPurchaseAsync", "popupShowAsync", "preferenceRelationshipListAsync", "Lcom/swipefwd/data/models/RelationshipListModel;", "recoverAccountAsync", "recoverAccountEmailAsync", "Lcom/swipefwd/data/models/RecoverAccountModel;", "recoverEmail", "recoverEmailAsync", "refreshToken", "registerUserAsync", "relationshipListAsync", "religionListAsync", "Lcom/swipefwd/data/models/ReligionListModel;", "removeTribeDaterAsync", "resendOtp", "Lcom/swipefwd/data/models/ResendOtp;", "sendBasicProfileData", "LBasicProfileDetails;", "sendContactAsync", "Lcom/swipefwd/data/models/FwdContactListResponseModel;", "Lcom/swipefwd/data/models/ContactListResponseModel;", "sendFacebookIdsAsync", "sendInstaImagesAsync", "Lcom/swipefwd/data/models/InstagramUploadResponseModel;", "sendOtpEmail", "sendReceiptAsync", "setDetailedProfile", "Lcom/swipefwd/data/models/DetailedProfileModel;", "setEmailUserApi", "Lcom/swipefwd/data/models/SetEmailModel;", "setLocation", "Lcom/swipefwd/data/models/LocationModel;", "setUserTypeApi", "Lcom/swipefwd/data/models/UserTypeModel;", "smokingListAsync", "Lcom/swipefwd/data/models/SmokingListModel;", "submitGestureVerification", "Lcom/swipefwd/data/models/GestureVerificationModel;", "requestBodyUserId", "suggestionProfileAsync", "Lcom/swipefwd/data/models/InviteModel;", "swipingLeftRightAsync", "Lcom/swipefwd/data/models/SwipeLeftRightResponseModel;", "swipingMatchesListAsync", "(Ljava/lang/String;Ljava/lang/Integer;)Lkotlinx/coroutines/Deferred;", "swipingProfileListAsync", "Lcom/swipefwd/data/models/SwipingProfileModel;", "swipingRewindAsync", "swipingRewindAsyncOld", "travelLocationAPi", "tribeConnectionAsync", "tribeConnectionListAsync", "Lcom/swipefwd/data/models/DaterConnectionResponseModel;", "Lcom/swipefwd/data/models/TribeDaterConnectionsResponseModel;", "updateDateList", "Lcom/swipefwd/data/models/UpdateDateModel;", "updateDeviceToken", "Lcom/swipefwd/data/models/DeviceTokenResponse;", "updateMobile", "updateTravelLocationAsync", "uploadChatImage", "Lcom/swipefwd/xmpp/ChatImageModel;", "uplodProfilePhotos", "LProfilePhotosModel;", "positionPhotos", "profile_pic", "userAdvancePreferenceSubmitAsync", "userBasicProfileSet", "LBasicProfileUpload;", "userLogoutAsync", "userPictureDeleteAsync", "LProfilePhotosModel$DataProfile$UserPhotos;", "userPreferenceSubmitAsync", "userPreferenceUpdateAsync", "userProfilePictureAsync", "is_profile", "image_file", "userProfilePictureDeleteAsync", "userProfileSubmitAsync", "userProfileUpdateAsync", "userSettingsUpdateAsync", "verifyOtpAsync", "verifyOtpEmail", "app_debug"})
public abstract interface ApiService {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "auth/login/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.LoginResponseModel>> loginUserAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "auth/register/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.LoginResponseModel>> registerUserAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/signup")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<OTPModel>> getOtpAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/updateMobile")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<OTPModel>> updateMobile(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/socialSignupCheck")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<OTPModel>> getSocialsignUp(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/setPreferences")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> userPreferenceSubmitAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/setAdvancePreferences")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> userAdvancePreferenceSubmitAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/setBasicProfile")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<BasicProfileDetails>> sendBasicProfileData(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/setAdvanceProfile")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.DetailedProfileModel>> setDetailedProfile(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/picture1/")
    @retrofit2.http.Multipart()
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> userProfilePictureAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Part(value = "is_profile")
    okhttp3.RequestBody is_profile, @org.jetbrains.annotations.Nullable()
    @retrofit2.http.Part()
    okhttp3.MultipartBody.Part image_file);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/uploadProfilepic")
    @retrofit2.http.Multipart()
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<BasicProfileUpload>> userBasicProfileSet(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.Nullable()
    @retrofit2.http.Part()
    okhttp3.MultipartBody.Part profile_pic);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/uploadPhotos")
    @retrofit2.http.Multipart()
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<ProfilePhotosModel>> uplodProfilePhotos(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Part(value = "position")
    okhttp3.RequestBody positionPhotos, @org.jetbrains.annotations.Nullable()
    @retrofit2.http.Part()
    okhttp3.MultipartBody.Part profile_pic);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/otpVerify")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<OTPModel>> verifyOtpAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/setUserEmail")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.SetEmailModel>> setEmailUserApi(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/verifyOTPEmail")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<OTPModel>> verifyOtpEmail(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/sendOTPEmail")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.ResendOtp>> sendOtpEmail(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/dateList")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.DateGetModel>> getDateList(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/updatedateList")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.UpdateDateModel>> updateDateList(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/setUsertype")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.UserTypeModel>> setUserTypeApi(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/resendOtp")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.ResendOtp>> resendOtp(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/setlocation")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.LocationModel>> setLocation(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "cms/language/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.LanguageListModel>> languageListAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "page")
    java.lang.String page);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "dropdown/language")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.LanguageDataModel>> getLanguageApi(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "cms/education/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.EducationLevelListModel>> educationLevelListAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "page")
    java.lang.String page);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "dropdown/education")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.EducationDetailModel>> getEducation(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "dropdown/astrological_sign")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.AstrologicalModel>> getAstrologicalSign(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/deleteUser")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.DeleteAccountModel>> deleteUserAccount(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "dropdown/relationship_level")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.RelationshipModel>> getRelationship(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "dropdown/children")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.ChildrenModel>> getChildren(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "dropdown/religion")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.ReligionModel>> getReligion(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "dropdown/smoking")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.SmokingDataModel>> getSmokingApi(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "dropdown/vaccination")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.VaccinationModel>> getVaccination(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "cms/astrological/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.AstroSignListModel>> astrologySignListAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "page")
    java.lang.String page);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "cms/cast/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CastListModel>> castListAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "page")
    java.lang.String page);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "cms/children/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.ChildrenListModel>> childrenListAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "page")
    java.lang.String page);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "cms/religion/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.ReligionListModel>> religionListAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "page")
    java.lang.String page);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "cms/smoking/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.SmokingListModel>> smokingListAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "page")
    java.lang.String page);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "cms/looking/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.RelationshipListModel>> relationshipListAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "page")
    java.lang.String page);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/profile/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> userProfileSubmitAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/refreshToken")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.RefreshTokenModel>> refreshToken(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST()
    public abstract retrofit2.Call<com.swipefwd.data.models.RefreshTokenModel> callWebservice(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Url()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/removeProfilepic")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<ProfilePhotosModel.DataProfile.UserPhotos>> userProfilePictureDeleteAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/removePhoto")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<ProfilePhotosModel.DataProfile.UserPhotos>> userPictureDeleteAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET()
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.InstitutesModel>> getInstitutesAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Url()
    java.lang.String url);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "cms/relationship/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.RelationshipListModel>> preferenceRelationshipListAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "page")
    java.lang.String page);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "cms/vaccinated/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CovidVaccineListModel>> covidVaccineListAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "page")
    java.lang.String page);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/preference/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> userPreferenceSubmitAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.PATCH(value = "user/profile/{id}/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> userProfileUpdateAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @retrofit2.http.Path(value = "id")
    int id, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.PATCH(value = "user/preference/{id}/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> userPreferenceUpdateAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @retrofit2.http.Path(value = "id")
    int id, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/recover/email/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> recoverEmailAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/emailrecovery")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> recoverEmail(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "auth/email/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> recoverAccountAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/contact/verify/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.FwdContactListResponseModel>> sendContactAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/home/syncContact")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.ContactListResponseModel>> sendContactAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "contact/sms/invite/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> inviteContactAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "tribe/connection/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> tribeConnectionAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/home/inviteConnector")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.InviteConnectorResponseModel>> inviteConnector(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "tribe/connection_v2/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.DaterConnectionResponseModel>> tribeConnectionListAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/home/home")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.TribeDaterConnectionsResponseModel>> tribeConnectionListAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/usertype/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> changeUserTypeAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "user/swipe_profiles/{id}/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.SwipingProfileMatchesModel>> swipingMatchesListAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.Nullable()
    @retrofit2.http.Path(value = "id")
    java.lang.Integer id);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "profiles/{id}")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.SwipingProfileModel>> swipingProfileListAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @retrofit2.http.Path(value = "id")
    int id);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/swipes/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.SwipeLeftRightResponseModel>> swipingLeftRightAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.HTTP(method = "DELETE", path = "user/recall/", hasBody = true)
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.SwipeLeftRightResponseModel>> swipingRewindAsyncOld(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject, @retrofit2.http.Path(value = "id")
    int id);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/recall/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.SwipeLeftRightResponseModel>> swipingRewindAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "user/profile/{id}/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.UserProfileResponseModel>> getUserProfileAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @retrofit2.http.Path(value = "id")
    int id);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "user/matches/{id}/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.SwipingProfileMatchesModel>> getMatchesListAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @retrofit2.http.Path(value = "id")
    int id);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "user/picture1/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.UserImagesResponseModel>> getUserProfileImagesAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "page")
    java.lang.String page);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "user/preference/{id}/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.UserPreferenceResponseModel>> getUserPreferenceAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @retrofit2.http.Path(value = "id")
    int id);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.PATCH(value = "user/settings/{id}/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> userSettingsUpdateAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @retrofit2.http.Path(value = "id")
    int id, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "user/settings/{id}/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.UserSettingsResponseModel>> getUserSettingsAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @retrofit2.http.Path(value = "id")
    int id);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/instagram/picture/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.InstagramUploadResponseModel>> sendInstaImagesAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/device/token/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.DeviceTokenResponse>> updateDeviceToken(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "tribe/remove/connection/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> removeTribeDaterAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/suggestion/profile/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.InviteModel>> suggestionProfileAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "account/plan/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.SettingSubscriptionModel>> getUserPlansAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "account/subscript/plan/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> planPurchaseAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.PATCH(value = "tribe/connection_v1/{id}/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> popupShowAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @retrofit2.http.Path(value = "id")
    int id);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "account/booster/subscription/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> boosterPurchaseAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.PATCH(value = "user/settings/{id}/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> deleteAccountAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @retrofit2.http.Path(value = "id")
    int userId, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "auth/logout/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> userLogoutAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "tribe/manage/connection/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.ActiveExpireRequestListModel>> getActivePendingRequestAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "tribe/manage/connection/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> addActivePendingRequestAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "tribe/dater/connection/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.ActiveExpireRequestListModel>> getActivityPendingRequestAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "auth/lastactivity/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.InactivityModel>> getInactivtyRequestAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/facebook/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> sendFacebookIdsAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "connections/{id}")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.FacebookMutualFriendsListModel>> getFacebookIdsAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @retrofit2.http.Path(value = "id")
    int userId, @retrofit2.http.Query(value = "id")
    int daterId);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "validations/photos")
    @retrofit2.http.Multipart()
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.ImageModerationResponseModel>> imageModerationAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.Nullable()
    @retrofit2.http.Part()
    okhttp3.MultipartBody.Part photo);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "testimage.php")
    @retrofit2.http.Multipart()
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.xmpp.ChatImageModel>> uploadChatImage(@org.jetbrains.annotations.Nullable()
    @retrofit2.http.Part()
    okhttp3.MultipartBody.Part photo);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "account/subscription/receipt/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> sendReceiptAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "account/travel/location/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.CommonResponseModel>> updateTravelLocationAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/setTravellocation")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.LocationModel>> travelLocationAPi(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "auth/update/phonenumber/")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.RecoverAccountModel>> recoverAccountEmailAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "maps/api/geocode/json")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.walletModels.GoogleAddressModel>> getAddressFromLatLngAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "key")
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "latlng")
    java.lang.String latlng);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/verifyProfile/")
    @retrofit2.http.Multipart()
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.GestureVerificationModel>> submitGestureVerification(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Part(value = "user_id")
    okhttp3.RequestBody requestBodyUserId, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Part(value = "gender")
    okhttp3.RequestBody requestBodyGender, @org.jetbrains.annotations.Nullable()
    @retrofit2.http.Part()
    okhttp3.MultipartBody.Part photo);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "user/verifyProfile")
    @retrofit2.http.Multipart()
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.GestureVerificationProfileModel>> gestureProfileVerification(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.HeaderMap()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Part(value = "gender")
    okhttp3.RequestBody requestBodyGender, @org.jetbrains.annotations.Nullable()
    @retrofit2.http.Part()
    okhttp3.MultipartBody.Part photo);
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public final class DefaultImpls {
    }
}