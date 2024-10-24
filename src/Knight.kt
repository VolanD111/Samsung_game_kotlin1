import kotlin.system.exitProcess

class Knight:Human(), attack, heal {
    var defence = 13

    fun check(checkin: Any)
    {
        if (checkin is Robot && checkin.health < 0)
        {
            println("Умер робот")
            exitProcess(0)
        }
        else if (checkin is Knight && checkin.health < 0)
        {
            println("Об этом рыцаре будут слагать легенды")
            exitProcess(0)
        }
        else if (checkin is Wizard && checkin.health < 0)
        {
            println("Умер маг")
            exitProcess(0)
        }
    }

    override fun heal(target: Any) {
        if (target is Wizard)
        {
            target.health += (0..3).random()
            if (target.health > 100)
                target.health = 100
        }
        else if (target is Knight)
        {
            target.health += (0..3).random()
            if (target.health > 100)
                target.health = 100
        }
        else if (target is Robot) {
            target.health += (0..3).random()
            if (target.health > 100)
                target.health = 100
        }
    }


    override fun attack(target: Any) {
        if (target is Robot) {
            if (target.battery >= 10)
            {
                target.battery -= 5
                target.health -= 7
            }
            else
            {
                target.battery += 2
                target.health -= 5
            }
            check(target)
            heal(target)
        }
        else if (target is Wizard)
        {
            target.health - 15
            check(target)
            heal(target)
        }
        else if (target is Knight)
        {
            if (target.defence > 0)
            {
                target.health -= 7
                target.defence -= 2
            }
            else
            {
                target.health -= 10
            }
            check(target)
            heal(target)
        }
    }
}