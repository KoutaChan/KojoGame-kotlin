package kojogame.koutachan.model



import kojogame.koutachan.KojoGame.Companion.plugin
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.Listener
import org.bukkit.scoreboard.DisplaySlot
import java.time.LocalDateTime


object scoreboard : Listener {

    fun ScoreBoardUpdate() { //多分プレイヤーごとにスコアボードは使わないから1個だけの対応でOK
        Bukkit.getScheduler().runTaskTimer(plugin, Runnable {
            Bukkit.getScoreboardManager().mainScoreboard.getObjective("KojoGame")?.unregister()
            Bukkit.getScoreboardManager().mainScoreboard.registerNewObjective("KojoGame", "dummy")
            val obj = Bukkit.getScoreboardManager().mainScoreboard.getObjective("KojoGame")
            val time = LocalDateTime.now()
            obj.setDisplaySlot(DisplaySlot.SIDEBAR)
            obj.setDisplayName("${ChatColor.YELLOW}[ Kojo Game (${Bukkit.getOnlinePlayers().size}) ]")
            obj.getScore("§7${time.year}/${time.month.value}/${time.dayOfMonth} §80.2β").setScore(10) //${time.hour}:${time.minute}:${time.second}
            obj.getScore("").setScore(9)
            obj.getScore("-----------------------------------").setScore(7)
            GameStateUpdate() //Line 8
            IronUpdate() //Line 6
            GoldUpdate() //Line 5
            DiamondUpdate() //Line 4
    }, 0, 20)
    }
    fun GameStateUpdate() {
        if (kojogame.koutachan.util.GameState.GameState == 0) {
            Bukkit.getScoreboardManager().mainScoreboard.getObjective("KojoGame").getScore(" » ゲーム待機中").setScore(8)
        } else if (kojogame.koutachan.util.GameState.GameState == 1) {
            Bukkit.getScoreboardManager().mainScoreboard.getObjective("KojoGame").getScore(" » ゲーム開始中").setScore(8)
        } else if (kojogame.koutachan.util.GameState.GameState == 2) {
            Bukkit.getScoreboardManager().mainScoreboard.getObjective("KojoGame").getScore(" » ゲーム中").setScore(8)
        } else if (kojogame.koutachan.util.GameState.GameState == 3) {
            Bukkit.getScoreboardManager().mainScoreboard.getObjective("KojoGame").getScore(" » ゲーム終了").setScore(8)
        }
    }
    fun IronUpdate() {
        if (kojogame.koutachan.util.IronSponges.IronSponges == true) {
            Bukkit.getScoreboardManager().mainScoreboard.getObjective("KojoGame").getScore(" スポンジ(1) » §c破壊されています").setScore(6)
        } else if (kojogame.koutachan.util.IronSponges.IronSponges == false) {
            Bukkit.getScoreboardManager().mainScoreboard.getObjective("KojoGame").getScore(" スポンジ(1) » §a破壊されていません").setScore(6)
        }
    }
    fun GoldUpdate() {
        if (kojogame.koutachan.util.GoldSponges.GoldSponges == true) {
            Bukkit.getScoreboardManager().mainScoreboard.getObjective("KojoGame").getScore(" §6スポンジ(2) » §c破壊されています").setScore(5)
        } else if (kojogame.koutachan.util.GoldSponges.GoldSponges == false) {
            Bukkit.getScoreboardManager().mainScoreboard.getObjective("KojoGame").getScore(" §6スポンジ(2) » §a破壊されていません").setScore(5)
        }
    }
    fun DiamondUpdate() {
        if (kojogame.koutachan.util.DiamondSponges.DiamondSponges == true) {
            Bukkit.getScoreboardManager().mainScoreboard.getObjective("KojoGame").getScore(" §bスポンジ(3) » §c破壊されています").setScore(4)
        } else if (kojogame.koutachan.util.DiamondSponges.DiamondSponges == false) {
            Bukkit.getScoreboardManager().mainScoreboard.getObjective("KojoGame").getScore(" §bスポンジ(3) » §a破壊されていません").setScore(4)
        }
    }
}