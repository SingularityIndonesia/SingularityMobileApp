package infra.auth

interface AuthDB {
    companion object {
        private var instance: AuthDB? = null

        suspend fun Instance(): AuthDB {
            if (instance == null) {
                val authDBClient = AuthDBClient()
                authDBClient.initializeDatabase().getOrThrow()
                instance = authDBClient
            }
            return instance!!
        }

        private var testInstance: AuthDB? = null

        suspend fun TestInstance(): AuthDB {
            if (testInstance == null) {
                val authDBClient = AuthDBClient()
                authDBClient.initializeTestingDatabase().getOrThrow()
                testInstance = authDBClient
            }
            return testInstance!!
        }
    }

    suspend fun initializeDatabase(): Result<Unit> {
        // return runCatching(Dispatchers.IO) {
        //     DatabaseConnection.connection()
        //     newSuspendedTransaction {
        //         SchemaUtils.create(LoginFormsTable)
        //     }
        // }
        TODO()
    }

    suspend fun initializeTestingDatabase(): Result<Unit> {
        // return runCatching(Dispatchers.IO) {
        //     DatabaseConnection.testConnection()
        //     newSuspendedTransaction {
        //         SchemaUtils.create(LoginFormsTable)
        //     }
        // }
        TODO()
    }
}

