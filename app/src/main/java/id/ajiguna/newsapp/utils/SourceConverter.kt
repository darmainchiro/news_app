package id.ajiguna.newsapp.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ajiguna.newsapp.source.news.SourceModel

object SourceConverter {
    @TypeConverter
    @JvmStatic
    fun toSource(value: String?) : SourceModel {
        val modelType = object : TypeToken<SourceModel>(){}.type
        return Gson().fromJson(value, modelType)
    }

    @TypeConverter
    @JvmStatic
    fun fromSource(source: SourceModel) : String {
        val gson = Gson()
        return gson.toJson(source)
    }
}