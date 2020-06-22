package com.example.startapp


import android.app.Application
import com.parse.*


    class App : Application() {

        override fun onCreate() {
            super.onCreate()

    //3
            // Enable Local Datastore.
            Parse.enableLocalDatastore(this)

            //1
         //  ParseObject.registerSubclass(User::class.java)
   //2
            Parse.initialize(Parse.Configuration.Builder(this)
                    .applicationId(BuildConfig.APP_ID)
                    .clientKey(BuildConfig.CLIENT_KEY)
                    .server(BuildConfig.SERVER_URL)
                    .build()
            )


    //4-a Create Example on parse name,phone
    //4-b Use the following code to insert data
//            val obj = ParseObject("Example")
//            obj.put("name","Ahmad")
//            obj.put("phone","00971506708115")



    //5
            ParseUser.enableAutomaticUser()
    //6
            val defaultACL = ParseACL()
            defaultACL.publicReadAccess = true
            defaultACL.publicWriteAccess = true
            ParseACL.setDefaultACL(defaultACL, true)





        }

    }