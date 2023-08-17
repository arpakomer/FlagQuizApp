package com.arpak.flagquizapp

class Bayraklardao {

    fun bring5RandomFlags(dtHelper: DataHelper) : ArrayList<Bayraklar>{

        val flagList = ArrayList<Bayraklar>()

        val db = dtHelper.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM bayraklar ORDER BY RANDOM() LIMIT 5",null)
        while (cursor.moveToNext()){
            val bayrak = Bayraklar(cursor.getInt(cursor.getColumnIndexOrThrow("bayrak_id"))
                ,cursor.getString(cursor.getColumnIndexOrThrow("bayrak_ad"))
                 ,cursor.getString(cursor.getColumnIndexOrThrow("bayrak_resim"))
                )
            flagList.add(bayrak)
        }

      return  flagList
    }

    fun bring3FalseOptionRandom(dtHelper: DataHelper, bayrak_id : Int) : ArrayList<Bayraklar> {
        val flagList = ArrayList<Bayraklar>()
        val db = dtHelper.writableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM bayraklar  WHERE bayrak_id != $bayrak_id ORDER BY RANDOM() LIMIT 3",
            null
        )
        while (cursor.moveToNext()) {
            val flag = Bayraklar(
                cursor.getInt(cursor.getColumnIndexOrThrow("bayrak_id")),
                cursor.getString(cursor.getColumnIndexOrThrow("bayrak_ad")),
                cursor.getString(cursor.getColumnIndexOrThrow("bayrak_resim"))
            )
            flagList.add(flag)
        }
            return flagList
    }

}

