package felix.phelippe.modulotechtest.models.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import felix.phelippe.modulotechtest.models.classes.*
import felix.phelippe.modulotechtest.models.database.dao.DeviceDao
import felix.phelippe.modulotechtest.models.database.dao.MainUserDao

@Database(entities = [(Heater::class),(Light::class),(RollerShutter::class),(MainUser::class)],version = 1, exportSchema = false)
    abstract class TestDatabase : RoomDatabase() {
        abstract fun deviceDao():DeviceDao
        abstract fun mainUserDao():MainUserDao

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