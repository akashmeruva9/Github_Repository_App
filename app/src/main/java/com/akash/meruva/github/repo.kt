package com.akash.meruva.github

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repo_table")
class repo(
    @ColumnInfo(name = "repo_name") var repo_name: String?,
    @ColumnInfo(name = "user_name") var user_name: String?,
    @ColumnInfo(name = "descreption") var descreption: String?,
    @ColumnInfo(name = "share_url") var share_url: String?,
    @ColumnInfo(name = "image_url") var image_url: String?,
    @ColumnInfo(name = "language") var language: String?,
    @ColumnInfo(name = "date_creatd") var datecreated: String?
)
{
    @PrimaryKey(autoGenerate = true) var id = 0
}