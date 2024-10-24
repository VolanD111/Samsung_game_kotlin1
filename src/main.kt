fun main() {
    val robot1 = Robot()
    val wizard1 = Wizard()
    val knight1 = Knight()

    knight1.heal(knight1)
    wizard1.attack(knight1)
    robot1.attack(knight1)
    println(knight1.health)
}