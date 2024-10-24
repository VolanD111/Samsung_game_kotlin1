import kotlin.system.exitProcess

class Wizard:Human(), attack, heal {
    var mana = 100

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
        if (target is Wizard) {
            if (this.mana > 5)
            {
                target.health -= 15
                this.mana -= 4
            }
            else
            {
                target.health -= 12
                this.mana += 3
            }
            check(target)
            heal(target)
        }
        else if (target is Robot){
            if (target.battery >= 5 && this.mana >= 5) // сильный удар и блок
            {
                target.battery -= 5
                target.health -= 9
                this.mana - 4
            }
            else if (target.battery < 5 && this.mana >= 5) // сильный удар без блока
            {
                target.battery += 2
                target.health -= 14
                this.mana -= 4
            }
            else if (target.battery >= 5 && this.mana < 5) // блок и слабый удар
            {
                target.battery -= 5
                target.health -= 4
                this.mana += 1
            }
            else // слабый удар без блока
            {
                target.battery += 2
                target.health -= 6
                this.mana += 1
            }
            check(target)
            heal(target)
        }
        else if (target is Knight) {
            if (this.mana >= 5 && target.defence > 0) // сильный удар и блокировка через броню рыцаря
            {
                target.health -= 8
                target.defence -= 2
                this.mana -= 5
            } else if (this.mana >= 5 && target.defence <= 0) {
                target.health -= 10
                this.mana -= 5
            } else if (this.mana < 5 && target.defence > 0)// слабый удар и блокировка
            {
                target.health -= 3
                target.defence -= 1
                this.mana += 1
            } else if (this.mana < 5 && target.defence <= 0) {
                target.health -= 7
                this.mana += 1
            }
            check(target)
            heal(target)
        }
    }
}