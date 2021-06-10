package felix.phelippe.modulotechtest.models.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import felix.phelippe.modulotechtest.models.classes.Heaters
import felix.phelippe.modulotechtest.models.classes.Light
import felix.phelippe.modulotechtest.models.classes.RollerShutter
import felix.phelippe.modulotechtest.models.classes.User
import felix.phelippe.modulotechtest.models.dao.DeviceDao
import felix.phelippe.modulotechtest.models.dao.UserDao

@Database(entities = [(Heaters::class), (Light::class),(RollerShutter::class), (User::class)],version = 1, exportSchema = false)
    abstract class TestDatabase : RoomDatabase() {
        abstract fun deviceDao():DeviceDao
        abstract fun userDao():UserDao

        companion object {
            private var INSTANCE: TestDatabase? = null

            fun getInstance(context: Context):TestDatabase{
                if (INSTANCE == null){
                    synchronized(this){
                        INSTANCE = Room.databaseBuilder(context.applicationContext,TestDatabase::class.java,"TestDatabase.db").build()
                    }
                }
                return INSTANCE as TestDatabase
            }

            fun destroyInstance(){
                INSTANCE = null
            }
        }
    }