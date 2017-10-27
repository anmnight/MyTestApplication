package unit

/**
 * Created by anmnight on 2017/10/27 0027.
 */
object TestCase {

    /**
     * 效率底下的斐波那契数列, 耗时的操作
     * @param num
     * @return
     */
    fun fibc(num: Int): Int {
        if (num == 0) {
            return 0
        }
        return if (num == 1) {
            1
        } else fibc(num - 1) + fibc(num - 2)
    }

}