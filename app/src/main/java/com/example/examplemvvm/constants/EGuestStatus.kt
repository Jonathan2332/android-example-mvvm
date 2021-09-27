package com.example.examplemvvm.constants

enum class EGuestStatus(val status: String)
{
    NOT_CONFIRMED("not_confirmed"),
    PRESENT("present"),
    ABSENT("absent");

    companion object
    {
        fun getStatus(input: String) : EGuestStatus
        {
            for(presence in values())
            {
                if(presence.status == input)
                {
                    return presence
                }
            }
            return NOT_CONFIRMED
        }
    }
}