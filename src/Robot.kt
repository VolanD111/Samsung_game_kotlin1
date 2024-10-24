import kotlin.system.exitProcess

class Robot:Human(), attack, heal {
    var battery = 100

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
            if (this.battery >= 10 && target.battery >= 5) // сильная атака и блокирование входящего урона с помощью энергии
            {
                target.battery -= 5  // блок сильной атаки
                this.battery -= 10  // трата энергии на сильную атаку
                target.health -= 10  // потеря здоровья у таргета
            }
            else if (this.battery < 10 && target.battery >= 5) // слабая атака и блокировка
            {
                target.battery -= 5  // блок обычной атаки
                this.battery += 2  // пополнение энергии тк не было сильной атаки
                target.health -= 3  // потеря здоровья у таргета
            }
            else if (this.battery >= 10 && target.battery < 5) // блокировка и слабый удар
            {
                target.battery += 2  // пополнение энергии тк не было щита на блокировку
                this.battery -= 10  // трата энергии на сильную атаку
                target.health -= 14  // потеря здоровья у таргета
            }
            else // сильная атака и без блокировки
            {
                target.battery += 2
                this.battery -= 10
                target.health -= 14
            }
            check(target)
            heal(target)
        }
        else if (target is Wizard)
        {
            if (this.battery >= 10)
            {
                this.battery -= 10
                target.health -= 100 // -13
                target.mana += 1
            }
            else
            {
                this.battery += 2
                target.health -= 8
                target.mana += 1
            }
            check(target)
            heal(target)
        }
        else if (target is Knight)
        {
            if (this.battery >= 10 && target.defence > 0)
            {
                this.battery -= 10
                target.health -= 7
                target.defence -= 2
            }
            else if (this.battery < 10 && target.defence > 0)
            {
                this.battery += 2
                target.health -= 3
                target.defence -= 2
            }
            else if (this.battery < 10 && target.defence <= 0)
            {
                this.battery += 2
                target.health -= 3
            }
            else
            {
                this.battery -= 10
                target.health -= 10
            }
            check(target)
            heal(target)
        }
    }
}