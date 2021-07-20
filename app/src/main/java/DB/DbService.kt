package DB

import Models.Belgi

interface DbService {
    fun addLabel(belgi: Belgi)
    fun editLabel(belgi: Belgi):Int
    fun deleteLabel(belgi: Belgi)
    fun getAllLabel():ArrayList<Belgi>
}