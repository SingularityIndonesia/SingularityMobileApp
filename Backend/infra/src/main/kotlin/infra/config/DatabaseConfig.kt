package infra.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

object DatabaseConfig {
    @Volatile
    private var database: Database? = null
    private var dataSource: HikariDataSource? = null
    
    fun connect(
        driverClassName: String = "org.postgresql.Driver",
        jdbcUrl: String = "jdbc:postgresql://localhost:5432/memories",
        username: String = "root",
        password: String = "root",
        maximumPoolSize: Int = 10
    ): Database {
        return database ?: synchronized(this) {
            database ?: run {
                val config = HikariConfig().apply {
                    this.driverClassName = driverClassName
                    this.jdbcUrl = jdbcUrl
                    this.username = username
                    this.password = password
                    this.maximumPoolSize = maximumPoolSize
                    this.isAutoCommit = false
                    this.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
                    validate()
                }
                
                dataSource = HikariDataSource(config)
                database = Database.connect(dataSource!!)
                database!!
            }
        }
    }
    
    fun connectH2ForTesting(): Database {
        return connect(
            driverClassName = "org.h2.Driver",
            jdbcUrl = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;MODE=PostgreSQL",
            username = "sa",
            password = ""
        )
    }
    
    fun close() {
        synchronized(this) {
            dataSource?.close()
            dataSource = null
            database = null
        }
    }
}
