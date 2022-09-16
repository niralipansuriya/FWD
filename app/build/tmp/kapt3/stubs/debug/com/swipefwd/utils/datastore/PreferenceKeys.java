package com.swipefwd.utils.datastore;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b*\n\u0002\u0010\b\n\u0002\b.\n\u0002\u0010\u0006\n\u0002\b[\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0007R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0007R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0007R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0007R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0007R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0007R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0007R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0007R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0007R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0007R\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0007R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0007R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0007R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0007R\u0017\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0007R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0007R\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0007R\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0007R\u0017\u0010/\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0007R\u0017\u00101\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0007R\u0017\u00103\u001a\b\u0012\u0004\u0012\u0002040\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010\u0007R\u0017\u00106\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010\u0007R\u0017\u00108\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010\u0007R\u0017\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010\u0007R\u0017\u0010<\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010\u0007R\u0017\u0010>\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010\u0007R\u0017\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bA\u0010\u0007R\u0017\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bC\u0010\u0007R\u0017\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bE\u0010\u0007R\u0017\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bG\u0010\u0007R\u0017\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bI\u0010\u0007R\u0017\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bK\u0010\u0007R\u0017\u0010L\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bM\u0010\u0007R\u0017\u0010N\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bO\u0010\u0007R\u0017\u0010P\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bQ\u0010\u0007R\u0017\u0010R\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bS\u0010\u0007R\u0017\u0010T\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bU\u0010\u0007R\u0017\u0010V\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bW\u0010\u0007R\u0017\u0010X\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bY\u0010\u0007R\u0017\u0010Z\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b[\u0010\u0007R\u0017\u0010\\\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b]\u0010\u0007R\u0017\u0010^\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b_\u0010\u0007R\u0017\u0010`\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\ba\u0010\u0007R\u0017\u0010b\u001a\b\u0012\u0004\u0012\u00020c0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bd\u0010\u0007R\u0017\u0010e\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bf\u0010\u0007R\u0017\u0010g\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bh\u0010\u0007R\u0017\u0010i\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bj\u0010\u0007R\u0017\u0010k\u001a\b\u0012\u0004\u0012\u00020c0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bl\u0010\u0007R\u0017\u0010m\u001a\b\u0012\u0004\u0012\u0002040\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bn\u0010\u0007R\u000e\u0010o\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u0017\u0010p\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bq\u0010\u0007R\u0017\u0010r\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bs\u0010\u0007R\u0017\u0010t\u001a\b\u0012\u0004\u0012\u0002040\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bu\u0010\u0007R\u0017\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bw\u0010\u0007R\u0017\u0010x\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\by\u0010\u0007R\u0017\u0010z\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b{\u0010\u0007R\u0017\u0010|\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b}\u0010\u0007R\u0017\u0010~\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u007f\u0010\u0007R\u0019\u0010\u0080\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0081\u0001\u0010\u0007R\u0019\u0010\u0082\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0083\u0001\u0010\u0007R\u0019\u0010\u0084\u0001\u001a\b\u0012\u0004\u0012\u0002040\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0085\u0001\u0010\u0007R\u0019\u0010\u0086\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0087\u0001\u0010\u0007R\u0019\u0010\u0088\u0001\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0089\u0001\u0010\u0007R\u0019\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u008b\u0001\u0010\u0007R\u0019\u0010\u008c\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u008d\u0001\u0010\u0007R\u0019\u0010\u008e\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u008f\u0001\u0010\u0007R\u0019\u0010\u0090\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0091\u0001\u0010\u0007R\u0019\u0010\u0092\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0093\u0001\u0010\u0007R\u0019\u0010\u0094\u0001\u001a\b\u0012\u0004\u0012\u0002040\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0095\u0001\u0010\u0007R\u0019\u0010\u0096\u0001\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0097\u0001\u0010\u0007R\u0019\u0010\u0098\u0001\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0099\u0001\u0010\u0007R\u0019\u0010\u009a\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u009b\u0001\u0010\u0007R\u0019\u0010\u009c\u0001\u001a\b\u0012\u0004\u0012\u0002040\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u009d\u0001\u0010\u0007R\u0019\u0010\u009e\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u009f\u0001\u0010\u0007R\u0019\u0010\u00a0\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u00a1\u0001\u0010\u0007R\u0019\u0010\u00a2\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u00a3\u0001\u0010\u0007R\u0019\u0010\u00a4\u0001\u001a\b\u0012\u0004\u0012\u0002040\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u00a5\u0001\u0010\u0007R\u0019\u0010\u00a6\u0001\u001a\b\u0012\u0004\u0012\u0002040\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u00a7\u0001\u0010\u0007R\u0019\u0010\u00a8\u0001\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u00a9\u0001\u0010\u0007R\u0019\u0010\u00aa\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u00ab\u0001\u0010\u0007R\u0019\u0010\u00ac\u0001\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u00ad\u0001\u0010\u0007R\u0019\u0010\u00ae\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u00af\u0001\u0010\u0007R\u0019\u0010\u00b0\u0001\u001a\b\u0012\u0004\u0012\u0002040\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u00b1\u0001\u0010\u0007R\u0019\u0010\u00b2\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u00b3\u0001\u0010\u0007R\u0019\u0010\u00b4\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u00b5\u0001\u0010\u0007R\u0019\u0010\u00b6\u0001\u001a\b\u0012\u0004\u0012\u0002040\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u00b7\u0001\u0010\u0007R\u0019\u0010\u00b8\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u00b9\u0001\u0010\u0007R\u0019\u0010\u00ba\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u00bb\u0001\u0010\u0007R\u0019\u0010\u00bc\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\t\n\u0000\u001a\u0005\b\u00bd\u0001\u0010\u0007\u00a8\u0006\u00be\u0001"}, d2 = {"Lcom/swipefwd/utils/datastore/PreferenceKeys;", "", "()V", "INVITER_GENDER", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "getINVITER_GENDER", "()Landroidx/datastore/preferences/core/Preferences$Key;", "IS_PROFILE_VERIFIED", "", "getIS_PROFILE_VERIFIED", "PREF_ACCOUNT_TOOL_TIP", "getPREF_ACCOUNT_TOOL_TIP", "PREF_ACCOUNT_TYPE", "getPREF_ACCOUNT_TYPE", "PREF_ADV_PROFILE_FLAG", "getPREF_ADV_PROFILE_FLAG", "PREF_AREA", "getPREF_AREA", "PREF_ASTROLOGY_SIGN", "getPREF_ASTROLOGY_SIGN", "PREF_BIO", "getPREF_BIO", "PREF_CAST", "getPREF_CAST", "PREF_CHILDREN", "getPREF_CHILDREN", "PREF_CONNECTOR_DISABLED", "getPREF_CONNECTOR_DISABLED", "PREF_CONNECTOR_FACEBOOK_FRD_PERMISSION", "getPREF_CONNECTOR_FACEBOOK_FRD_PERMISSION", "PREF_CONNECTOR_TOOL_TIP", "getPREF_CONNECTOR_TOOL_TIP", "PREF_CONVERTED_DOB", "getPREF_CONVERTED_DOB", "PREF_COUNTRY_CODE", "getPREF_COUNTRY_CODE", "PREF_COVID", "getPREF_COVID", "PREF_CURRENT_SCREEN", "getPREF_CURRENT_SCREEN", "PREF_DARK_MODE_TOOL_TIP", "getPREF_DARK_MODE_TOOL_TIP", "PREF_DATER_DISABLED", "getPREF_DATER_DISABLED", "PREF_DOB", "getPREF_DOB", "PREF_DO_NOT_SHOW_DELETE_DATER", "getPREF_DO_NOT_SHOW_DELETE_DATER", "PREF_EDUCATION", "getPREF_EDUCATION", "PREF_END_AGE", "", "getPREF_END_AGE", "PREF_FACEBOOK_FRD_PERMISSION", "getPREF_FACEBOOK_FRD_PERMISSION", "PREF_FACEBOOK_IDS", "getPREF_FACEBOOK_IDS", "PREF_FIRST_NAME", "getPREF_FIRST_NAME", "PREF_FWD_GREEN_TOOL_TIP", "getPREF_FWD_GREEN_TOOL_TIP", "PREF_FWD_TOOL_TIP", "getPREF_FWD_TOOL_TIP", "PREF_GENDER", "getPREF_GENDER", "PREF_HEIGHT", "getPREF_HEIGHT", "PREF_IMAGES", "getPREF_IMAGES", "PREF_INSTA_ID", "getPREF_INSTA_ID", "PREF_INSTA_IMAGES", "getPREF_INSTA_IMAGES", "PREF_INSTA_NAME", "getPREF_INSTA_NAME", "PREF_INTERESTED", "getPREF_INTERESTED", "PREF_INVITE_EXPIRED", "getPREF_INVITE_EXPIRED", "PREF_IS_AGREE", "getPREF_IS_AGREE", "PREF_IS_DOB_DIALOG_OPEN", "getPREF_IS_DOB_DIALOG_OPEN", "PREF_IS_HEIGHT_FEET", "getPREF_IS_HEIGHT_FEET", "PREF_IS_NEW_USER", "getPREF_IS_NEW_USER", "PREF_IS_ONBOARD_TUTORIAL_COMPLETED", "getPREF_IS_ONBOARD_TUTORIAL_COMPLETED", "PREF_IS_SHOW_PROFILE", "getPREF_IS_SHOW_PROFILE", "PREF_IS_VERIFIED", "getPREF_IS_VERIFIED", "PREF_LANGUAGE", "getPREF_LANGUAGE", "PREF_LAST_NAME", "getPREF_LAST_NAME", "PREF_LAT", "", "getPREF_LAT", "PREF_LINKEDIN_ID", "getPREF_LINKEDIN_ID", "PREF_LINKEDIN_NAME", "getPREF_LINKEDIN_NAME", "PREF_LOGIN_FLAG", "getPREF_LOGIN_FLAG", "PREF_LONG", "getPREF_LONG", "PREF_MAX_DISTANCE", "getPREF_MAX_DISTANCE", "PREF_NAME", "PREF_NOT_INTERESTED", "getPREF_NOT_INTERESTED", "PREF_OCCUPATION", "getPREF_OCCUPATION", "PREF_OTP_COUNTER", "getPREF_OTP_COUNTER", "PREF_PHONE_NUMBER", "getPREF_PHONE_NUMBER", "PREF_PREFERENCE_FLAG", "getPREF_PREFERENCE_FLAG", "PREF_PREF_ASTROLOGY_SIGN", "getPREF_PREF_ASTROLOGY_SIGN", "PREF_PREF_CAST", "getPREF_PREF_CAST", "PREF_PREF_CHILDREN", "getPREF_PREF_CHILDREN", "PREF_PREF_EDUCATION", "getPREF_PREF_EDUCATION", "PREF_PREF_END_HEIGHT", "getPREF_PREF_END_HEIGHT", "PREF_PREF_END_HEIGHT_INCHES", "getPREF_PREF_END_HEIGHT_INCHES", "PREF_PREF_GENDER", "getPREF_PREF_GENDER", "PREF_PREF_IS_HEIGHT_FEET", "getPREF_PREF_IS_HEIGHT_FEET", "PREF_PREF_LANGUAGE", "getPREF_PREF_LANGUAGE", "PREF_PREF_RELATIONSHIP", "getPREF_PREF_RELATIONSHIP", "PREF_PREF_RELIGION", "getPREF_PREF_RELIGION", "PREF_PREF_SMOKING", "getPREF_PREF_SMOKING", "PREF_PREF_START_HEIGHT", "getPREF_PREF_START_HEIGHT", "PREF_PREF_START_HEIGHT_INCHES", "getPREF_PREF_START_HEIGHT_INCHES", "PREF_PREVIOUS_NUMBER", "getPREF_PREVIOUS_NUMBER", "PREF_PROFILE_FLAG", "getPREF_PROFILE_FLAG", "PREF_PROFILE_IMAGE", "getPREF_PROFILE_IMAGE", "PREF_PROFILE_PERCENTAGE", "getPREF_PROFILE_PERCENTAGE", "PREF_RECOVERY_EMAIL", "getPREF_RECOVERY_EMAIL", "PREF_RELATIONSHIP", "getPREF_RELATIONSHIP", "PREF_RELIGION", "getPREF_RELIGION", "PREF_REMAINING_CONNECTOR_CONNECTION", "getPREF_REMAINING_CONNECTOR_CONNECTION", "PREF_REMAINING_INVITATION", "getPREF_REMAINING_INVITATION", "PREF_SHOW_NOTIFICATION_DIALOG", "getPREF_SHOW_NOTIFICATION_DIALOG", "PREF_SMOKING", "getPREF_SMOKING", "PREF_SMS_SENT", "getPREF_SMS_SENT", "PREF_SOCIAL_MEDIA_USER", "getPREF_SOCIAL_MEDIA_USER", "PREF_START_AGE", "getPREF_START_AGE", "PREF_TOKEN", "getPREF_TOKEN", "PREF_TRAVEL_LOCATION", "getPREF_TRAVEL_LOCATION", "PREF_USER_ID", "getPREF_USER_ID", "SHOW_EMAIL_REMINDER_DATE", "getSHOW_EMAIL_REMINDER_DATE", "SHOW_VERIFICATION_REMINDER_DATE", "getSHOW_VERIFICATION_REMINDER_DATE", "TIMEOUT_OTP", "getTIMEOUT_OTP", "app_debug"})
public final class PreferenceKeys {
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.utils.datastore.PreferenceKeys INSTANCE = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PREF_NAME = "FWD_Preference";
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_LOGIN_FLAG = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_ACCOUNT_TYPE = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_LANGUAGE = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_OCCUPATION = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_EDUCATION = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_CAST = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_CHILDREN = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_RELIGION = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_SMOKING = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_RELATIONSHIP = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_COVID = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_ASTROLOGY_SIGN = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_FIRST_NAME = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_LAST_NAME = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_PROFILE_IMAGE = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_DOB = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_CONVERTED_DOB = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_GENDER = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_AREA = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_BIO = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_HEIGHT = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_IS_HEIGHT_FEET = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_IMAGES = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Double> PREF_LAT = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Double> PREF_LONG = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_INSTA_ID = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_INSTA_NAME = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_LINKEDIN_NAME = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_IS_SHOW_PROFILE = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_INSTA_IMAGES = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_IS_AGREE = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_INVITE_EXPIRED = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_FACEBOOK_FRD_PERMISSION = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_CONNECTOR_FACEBOOK_FRD_PERMISSION = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_TRAVEL_LOCATION = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_FACEBOOK_IDS = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> PREF_REMAINING_INVITATION = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> PREF_REMAINING_CONNECTOR_CONNECTION = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_IS_NEW_USER = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_PREF_LANGUAGE = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_PREF_EDUCATION = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_PREF_ASTROLOGY_SIGN = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_PREF_CAST = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_PREF_CHILDREN = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_PREF_RELIGION = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_PREF_SMOKING = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_PREF_RELATIONSHIP = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_PREF_START_HEIGHT = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_PREF_END_HEIGHT = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_IS_VERIFIED = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_PREF_GENDER = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> PREF_START_AGE = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> PREF_END_AGE = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> PREF_MAX_DISTANCE = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_RECOVERY_EMAIL = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_PREF_IS_HEIGHT_FEET = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> PREF_PREF_START_HEIGHT_INCHES = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> PREF_PREF_END_HEIGHT_INCHES = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> TIMEOUT_OTP = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_PROFILE_FLAG = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_TOKEN = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> PREF_OTP_COUNTER = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_SOCIAL_MEDIA_USER = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_PREFERENCE_FLAG = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_ADV_PROFILE_FLAG = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_CURRENT_SCREEN = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_IS_ONBOARD_TUTORIAL_COMPLETED = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_PHONE_NUMBER = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_COUNTRY_CODE = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> PREF_LINKEDIN_ID = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_PREVIOUS_NUMBER = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> PREF_USER_ID = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_ACCOUNT_TOOL_TIP = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_CONNECTOR_TOOL_TIP = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_DARK_MODE_TOOL_TIP = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_FWD_TOOL_TIP = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_FWD_GREEN_TOOL_TIP = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_NOT_INTERESTED = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_INTERESTED = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_DO_NOT_SHOW_DELETE_DATER = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> PREF_PROFILE_PERCENTAGE = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_DATER_DISABLED = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_CONNECTOR_DISABLED = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_SMS_SENT = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> SHOW_VERIFICATION_REMINDER_DATE = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> SHOW_EMAIL_REMINDER_DATE = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> IS_PROFILE_VERIFIED = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> INVITER_GENDER = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_SHOW_NOTIFICATION_DIALOG = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> PREF_IS_DOB_DIALOG_OPEN = null;
    
    private PreferenceKeys() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_LOGIN_FLAG() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_ACCOUNT_TYPE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_LANGUAGE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_OCCUPATION() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_EDUCATION() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_CAST() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_CHILDREN() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_RELIGION() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_SMOKING() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_RELATIONSHIP() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_COVID() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_ASTROLOGY_SIGN() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_FIRST_NAME() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_LAST_NAME() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_PROFILE_IMAGE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_DOB() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_CONVERTED_DOB() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_GENDER() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_AREA() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_BIO() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_HEIGHT() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_IS_HEIGHT_FEET() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_IMAGES() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Double> getPREF_LAT() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Double> getPREF_LONG() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_INSTA_ID() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_INSTA_NAME() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_LINKEDIN_NAME() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_IS_SHOW_PROFILE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_INSTA_IMAGES() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_IS_AGREE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_INVITE_EXPIRED() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_FACEBOOK_FRD_PERMISSION() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_CONNECTOR_FACEBOOK_FRD_PERMISSION() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_TRAVEL_LOCATION() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_FACEBOOK_IDS() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> getPREF_REMAINING_INVITATION() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> getPREF_REMAINING_CONNECTOR_CONNECTION() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_IS_NEW_USER() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_PREF_LANGUAGE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_PREF_EDUCATION() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_PREF_ASTROLOGY_SIGN() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_PREF_CAST() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_PREF_CHILDREN() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_PREF_RELIGION() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_PREF_SMOKING() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_PREF_RELATIONSHIP() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_PREF_START_HEIGHT() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_PREF_END_HEIGHT() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_IS_VERIFIED() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_PREF_GENDER() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> getPREF_START_AGE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> getPREF_END_AGE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> getPREF_MAX_DISTANCE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_RECOVERY_EMAIL() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_PREF_IS_HEIGHT_FEET() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> getPREF_PREF_START_HEIGHT_INCHES() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> getPREF_PREF_END_HEIGHT_INCHES() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getTIMEOUT_OTP() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_PROFILE_FLAG() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_TOKEN() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> getPREF_OTP_COUNTER() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_SOCIAL_MEDIA_USER() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_PREFERENCE_FLAG() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_ADV_PROFILE_FLAG() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_CURRENT_SCREEN() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_IS_ONBOARD_TUTORIAL_COMPLETED() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_PHONE_NUMBER() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_COUNTRY_CODE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getPREF_LINKEDIN_ID() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_PREVIOUS_NUMBER() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> getPREF_USER_ID() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_ACCOUNT_TOOL_TIP() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_CONNECTOR_TOOL_TIP() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_DARK_MODE_TOOL_TIP() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_FWD_TOOL_TIP() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_FWD_GREEN_TOOL_TIP() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_NOT_INTERESTED() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_INTERESTED() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_DO_NOT_SHOW_DELETE_DATER() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> getPREF_PROFILE_PERCENTAGE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_DATER_DISABLED() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_CONNECTOR_DISABLED() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_SMS_SENT() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getSHOW_VERIFICATION_REMINDER_DATE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getSHOW_EMAIL_REMINDER_DATE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getIS_PROFILE_VERIFIED() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getINVITER_GENDER() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_SHOW_NOTIFICATION_DIALOG() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getPREF_IS_DOB_DIALOG_OPEN() {
        return null;
    }
}