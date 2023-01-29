package com.areeb.adminportal.utils

interface Constants {

    interface AuthDetails {
        companion object {
            const val BASE_URL = "https://android-messaging.branch.co/"
            const val PREFS_TOKEN_FILE = "PREFS_TOKEN_MANAGE"
            const val USER_TOKEN = "USER_TOKEN_MANAGE"
        }
    }

    interface SharedPreferences {
        companion object {
            const val BRANCH_PREFERENCES = "branchPreferences"
        }
    }
}
