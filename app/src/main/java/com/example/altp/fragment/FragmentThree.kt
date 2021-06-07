package com.example.altp.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.altp.*
import com.example.altp.data.DataMoney
import com.example.altp.data.DataPerson
import com.example.altp.data.DataQuestion
import com.example.altp.databinding.FragmentThreeBinding
import com.skydoves.progressview.ProgressView
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.random.Random

class FragmentThree : BaseFragment(), View.OnClickListener {
    private var binding: FragmentThreeBinding? = null
    private var arr_Question = mutableListOf<DataQuestion>()
    private var currentps = 0
    private var delay: Long = 6500
    private var hander = Handler()
    private var cdown: CountDownTimer? = null
    private var mediaclass: MusicManager? = null
    private var arr_Money = mutableListOf<DataMoney>()

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThreeBinding.inflate(inflater, container, false)
        mediaclass = MusicManager(this!!.context!!)
        arr_Question = GetSQLData(this!!.context!!).getDataQuestion()
        getMoney()
        getQS(0)
        cdown!!.start()
        binding!!.xx1.visibility = View.INVISIBLE
        binding!!.xx2.visibility = View.INVISIBLE
        binding!!.xx3.visibility = View.INVISIBLE
        binding!!.txta.setOnClickListener(this)
        binding!!.txtxb.setOnClickListener(this)
        binding!!.txtc.setOnClickListener(this)
        binding!!.txtd.setOnClickListener(this)
        binding!!.imgic.startAnimation(AnimationUtils.loadAnimation(context, R.anim.rotatee))
        return binding!!.root
    }

    fun getQS(position: Int) {
        if (position == 15) {
            digmoney(15)
        } else {
            binding!!.txtnumber.setText("Câu ${arr_Question[position].number}:")
            binding!!.txtqs.setText(arr_Question[position].qs)
            binding!!.txta.setText("A: " + arr_Question[position].qsA)
            binding!!.txtxb.setText("B: " + arr_Question[position].qsB)
            binding!!.txtc.setText("C: " + arr_Question[position].qsC)
            binding!!.txtd.setText("D: " + arr_Question[position].qsD)
            binding!!.txtmoney.setText(arr_Money[14 - position].money)
            animation4answer()
            precent_50(position)
            binding!!.imgcall.setOnClickListener {
                digalogCallFamous(position)
                 binding!!.xx2.visibility = View.VISIBLE
                 binding!!.imgcall.isEnabled = false
            }
            binding!!.imghelp.setOnClickListener {
                mediaclass!!.callAudience()
                supportHelp(position)
                binding!!.xx3.visibility = View.VISIBLE
                binding!!.imghelp.isEnabled = false
            }
            countDown(position)
            mediaclass!!.start15qs(position)
        }

    }

    fun animation4answer() {
        binding!!.txta.visibility = View.INVISIBLE
        binding!!.txtxb.visibility = View.INVISIBLE
        binding!!.txtc.visibility = View.INVISIBLE
        binding!!.txtd.visibility = View.INVISIBLE

        var anim = AnimationUtils.loadAnimation(context, R.anim.animationn)
        hander.postDelayed(Runnable {
            binding!!.txta.visibility = View.VISIBLE
            binding!!.txta.startAnimation(anim)
        }, 1000)
        hander.postDelayed(Runnable {
            binding!!.txtxb.visibility = View.VISIBLE
            binding!!.txtxb.startAnimation(anim)
        }, 1500)
        hander.postDelayed(Runnable {
            binding!!.txtc.visibility = View.VISIBLE
            binding!!.txtc.startAnimation(anim)
        }, 2000)
        hander.postDelayed(Runnable {
            binding!!.txtd.visibility = View.VISIBLE
            binding!!.txtd.startAnimation(anim)
        }, 2500)
    }

    fun getMoney() {
        arr_Money.add(DataMoney("$150.000"))
        arr_Money.add(DataMoney("$85.000"))
        arr_Money.add(DataMoney("$60.000"))
        arr_Money.add(DataMoney("$40.000"))
        arr_Money.add(DataMoney("$30.000"))
        arr_Money.add(DataMoney("$22.000"))
        arr_Money.add(DataMoney("$14.000"))
        arr_Money.add(DataMoney("$10.000"))
        arr_Money.add(DataMoney("$6.000"))
        arr_Money.add(DataMoney("$3.000"))
        arr_Money.add(DataMoney("$2.000"))
        arr_Money.add(DataMoney("$1.000"))
        arr_Money.add(DataMoney("$600"))
        arr_Money.add(DataMoney("$400"))
        arr_Money.add(DataMoney("$200"))
    }

    override fun onClick(v: View?) {
        cdown!!.cancel()
        when (v!!.id) {

            R.id.txta -> {
                mediaclass!!.readAnswerA()
                binding!!.txtxb.isEnabled = false
                binding!!.txtc.isEnabled = false
                binding!!.txtd.isEnabled = false
                checkAnswer(binding!!.txta, binding!!.txtxb, binding!!.txtc, binding!!.txtd)
            }
            R.id.txtxb -> {
                mediaclass!!.readAnswerB()
                binding!!.txta.isEnabled = false
                binding!!.txtc.isEnabled = false
                binding!!.txtd.isEnabled = false
                checkAnswer(binding!!.txta, binding!!.txtxb, binding!!.txtc, binding!!.txtd)
            }
            R.id.txtc -> {
                mediaclass!!.readAnswerC()
                binding!!.txtxb.isEnabled = false
                binding!!.txta.isEnabled = false
                binding!!.txtd.isEnabled = false
                checkAnswer(binding!!.txta, binding!!.txtxb, binding!!.txtc, binding!!.txtd)
            }
            R.id.txtd -> {
                mediaclass!!.readAnswerD()
                binding!!.txtxb.isEnabled = false
                binding!!.txtc.isEnabled = false
                binding!!.txta.isEnabled = false
                checkAnswer(binding!!.txta, binding!!.txtxb, binding!!.txtc, binding!!.txtd)
            }
        }
    }

    fun checkAnswer(va: TextView, vb: TextView, vc: TextView, vd: TextView) {

        if (va.isEnabled) {

            Toast.makeText(context, "click a", Toast.LENGTH_SHORT).show()
            va.isEnabled = false
            va.setBackgroundResource(R.drawable.bg_choose2)
            if (arr_Question[currentps].isTrue == 1) {
                currentps++
                hander.postDelayed(Runnable {
                    va.setBackgroundResource(R.drawable.ani_list)
                    var anim: AnimationDrawable = va.background as AnimationDrawable
                    anim.start()
                    mediaclass!!.readTrueA()
                }, 3000)


                hander.postDelayed(Runnable {
                    va.setBackgroundResource(R.drawable.bg_nomal)
                    va.isEnabled = true
                    vb.isEnabled = true
                    vc.isEnabled = true
                    vd.isEnabled = true
                    getQS(currentps)
                    cdown!!.start()
                }, delay)

            } else {
                hander.postDelayed(Runnable {
                    va.setBackgroundResource(R.drawable.bg_faile1)
                    checkanswerfalse(va, vb, vc, vd)

                }, 2500)
                hander.postDelayed(Runnable {
                    digmoney(currentps)
                }, 3500)
            }

        } else if (vb.isEnabled) {

            Toast.makeText(context, "click b", Toast.LENGTH_SHORT).show()
            vb.isEnabled = false
            vb.setBackgroundResource(R.drawable.bg_choose2)
            if (arr_Question[currentps].isTrue == 2) {
                currentps++

                hander.postDelayed(Runnable {
                    vb.setBackgroundResource(R.drawable.ani_list)
                    var anim: AnimationDrawable = vb.background as AnimationDrawable
                    anim.start()
                    mediaclass!!.readTrueB()
                }, 3000)


                hander.postDelayed(Runnable {
                    vb.setBackgroundResource(R.drawable.bg_nomal)
                    va.isEnabled = true
                    vb.isEnabled = true
                    vc.isEnabled = true
                    vd.isEnabled = true
                    getQS(currentps)
                    cdown!!.start()
                }, delay)


            } else {
                hander.postDelayed(Runnable {
                    vb.setBackgroundResource(R.drawable.bg_faile1)
                    checkanswerfalse(va, vb, vc, vd)

                }, 2500)
                hander.postDelayed(Runnable {
                    digmoney(currentps)
                }, 3500)
            }

        } else if (vc.isEnabled) {

            Toast.makeText(context, "click c", Toast.LENGTH_SHORT).show()
            vc.isEnabled = false
            vc.setBackgroundResource(R.drawable.bg_choose2)
            if (arr_Question[currentps].isTrue == 3) {
                currentps++

                hander.postDelayed(Runnable {
                    vc.setBackgroundResource(R.drawable.ani_list)
                    var anim: AnimationDrawable = vc.background as AnimationDrawable
                    anim.start()
                    mediaclass!!.readTrueC()
                }, 3000)


                hander.postDelayed(Runnable {
                    vc.setBackgroundResource(R.drawable.bg_nomal)
                    va.isEnabled = true
                    vb.isEnabled = true
                    vc.isEnabled = true
                    vd.isEnabled = true
                    getQS(currentps)
                    cdown!!.start()
                }, 6500)


            } else {
                hander.postDelayed(Runnable {
                    vc.setBackgroundResource(R.drawable.bg_faile1)
                    checkanswerfalse(va, vb, vc, vd)

                }, 2500)
                hander.postDelayed(Runnable {
                    digmoney(currentps)
                }, 3500)
            }

        } else if (vd.isEnabled) {

            Toast.makeText(context, "click d", Toast.LENGTH_SHORT).show()
            vd.isEnabled = false
            vd.setBackgroundResource(R.drawable.bg_choose2)
            if (arr_Question[currentps].isTrue == 4) {
                currentps++
                hander.postDelayed(Runnable {
                    vd.setBackgroundResource(R.drawable.ani_list)
                    var anim: AnimationDrawable = vd.background as AnimationDrawable
                    anim.start()
                    mediaclass!!.readTrueD()
                }, 3000)

                hander.postDelayed(Runnable {
                    vd.setBackgroundResource(R.drawable.bg_nomal)
                    va.isEnabled = true
                    vb.isEnabled = true
                    vc.isEnabled = true
                    vd.isEnabled = true
                    getQS(currentps)
//                    binding!!.txtmoney.setText(arr_Money[14 - currentps].money)
                    cdown!!.start()
                }, delay)

            } else {
                hander.postDelayed(Runnable {
                    vd.setBackgroundResource(R.drawable.bg_faile1)
                    checkanswerfalse(va, vb, vc, vd)

                }, 2500)
                hander.postDelayed(Runnable {
                    digmoney(currentps)
                }, 3500)
            }

        }

    }

    fun checkanswerfalse(va: TextView, vb: TextView, vc: TextView, vd: TextView) {

        if (arr_Question[currentps].isTrue == 1) {
            va.setBackgroundResource(R.drawable.ani_list)
            var anim: AnimationDrawable = va.background as AnimationDrawable
            anim.start()
            mediaclass!!.readFalseA()
        } else if (arr_Question[currentps].isTrue == 2) {
            vb.setBackgroundResource(R.drawable.ani_list)
            var anim: AnimationDrawable = vb.background as AnimationDrawable
            anim.start()
            mediaclass!!.readFalseB()
        } else if (arr_Question[currentps].isTrue == 3) {
            vc.setBackgroundResource(R.drawable.ani_list)
            var anim: AnimationDrawable = vc.background as AnimationDrawable
            anim.start()
            mediaclass!!.readFalseC()
        } else if (arr_Question[currentps].isTrue == 4) {
            vd.setBackgroundResource(R.drawable.ani_list)
            var anim: AnimationDrawable = vd.background as AnimationDrawable
            anim.start()
            mediaclass!!.readFalseD()
        }

    }

    fun precent_50(position: Int) {

        binding!!.img50.setOnClickListener {
            mediaclass!!.call50percent()
            hander.postDelayed(Runnable {
                if (arr_Question[position].isTrue == 1) {
                    binding!!.txtxb.setText("")
                    binding!!.txtxb.isEnabled = false
                    binding!!.txtd.setText("")
                    binding!!.txtd.isEnabled = false
                } else if (arr_Question[position].isTrue == 2) {
                    binding!!.txta.setText("")
                    binding!!.txta.isEnabled = false
                    binding!!.txtd.setText("")
                    binding!!.txtd.isEnabled = false
                } else if (arr_Question[position].isTrue == 3) {
                    binding!!.txta.setText("")
                    binding!!.txta.isEnabled = false
                    binding!!.txtxb.setText("")
                    binding!!.txtxb.isEnabled = false
                } else if (arr_Question[position].isTrue == 4) {
                    binding!!.txtc.setText("")
                    binding!!.txtc.isEnabled = false
                    binding!!.txtxb.setText("")
                    binding!!.txtxb.isEnabled = false
                }
            }, 2000)
            binding!!.xx1.visibility = View.VISIBLE
            binding!!.img50.isEnabled = false
        }
    }

    @SuppressLint("UseRequireInsteadOfGet")
    fun digalogCallFamous(position: Int) {
        mediaclass!!.callHelp()
        val dig = Dialog(this!!.context!!)
        dig.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dig.setContentView(R.layout.dig)
        dig.show()
        dig.setCancelable(false)
        val tvans = dig.findViewById<TextView>(R.id.txtans)
        val tvok = dig.findViewById<TextView>(R.id.txtok)
        tvans.visibility = View.INVISIBLE
        tvok.visibility = View.INVISIBLE
        val tv = dig.findViewById<TextView>(R.id.txtans)
        dig.findViewById<ImageView>(R.id.imgbill).setOnClickListener {
            answer(tv, tvans, tvok, position)
        }
        dig.findViewById<ImageView>(R.id.imgrol).setOnClickListener {
            answer(tv, tvans, tvok, position)
        }
        dig.findViewById<ImageView>(R.id.imgmes).setOnClickListener {
            answer(tv, tvans, tvok, position)
        }
        dig.findViewById<ImageView>(R.id.imgney).setOnClickListener {
            answer(tv, tvans, tvok, position)
        }
        dig.findViewById<TextView>(R.id.txtok).setOnClickListener {
            dig.dismiss()
        }

    }

    fun answer(va: TextView, tvans: TextView, tvok: TextView, position: Int) {
        tvans.visibility = View.VISIBLE
        tvok.visibility = View.VISIBLE
        if (arr_Question[position].isTrue == 1) {
            va.setText("Bạn nên chọn A")
        } else if (arr_Question[position].isTrue == 2) {
            va.setText("Bạn nên chọn B")
        } else if (arr_Question[position].isTrue == 3) {
            va.setText("Bạn nên chọn C")
        } else if (arr_Question[position].isTrue == 4) {
            va.setText("Bạn nên chọn D")
        }
    }

    @SuppressLint("UseRequireInsteadOfGet")
    fun supportHelp(position: Int) {

        var dig = Dialog(this!!.context!!)
        dig.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dig.setContentView(R.layout.progressview)
        dig.setCancelable(false)
        dig.show()
        var pra = dig.findViewById<ProgressView>(R.id.prA)
        var prb = dig.findViewById<ProgressView>(R.id.prB)
        var prc = dig.findViewById<ProgressView>(R.id.prC)
        var prd = dig.findViewById<ProgressView>(R.id.prD)
        var tva = dig.findViewById<TextView>(R.id.percentA)
        var tvb = dig.findViewById<TextView>(R.id.percentB)
        var tvc = dig.findViewById<TextView>(R.id.percentC)
        var tvd = dig.findViewById<TextView>(R.id.percentD)
        dig.findViewById<TextView>(R.id.ok).setOnClickListener {
            dig.dismiss()
        }
        if (position < 5) {
            val rd = Random
            val nbA = (rd.nextInt(30) + 70)
            val nbB = rd.nextInt(100 - nbA)
            val nbC = rd.nextInt(100 - nbB - nbA)
            val nbD = 100 - nbA - nbB - nbC
            val prA = nbA.toFloat()
            val prB = nbB.toFloat()
            val prC = nbC.toFloat()
            val prD = nbD.toFloat()
            hander.postDelayed(Runnable {
                trueCase(tva, tvb, tvc, tvd, prA, prB, prC, prD, pra, prb, prc, prd, position)
            }, 2500)

        } else if (position >= 5 && position <= 7) {
            val rd = Random
            val nbA = (rd.nextInt(15) + 40)
            val nbB = rd.nextInt(100 - nbA)
            val nbC = rd.nextInt(100 - nbB - nbA)
            val nbD = 100 - nbA - nbB - nbC
            val prA = nbA.toFloat()
            val prB = nbB.toFloat()
            val prC = nbC.toFloat()
            val prD = nbD.toFloat()
            hander.postDelayed(Runnable {
                trueCase(tva, tvb, tvc, tvd, prA, prB, prC, prD, pra, prb, prc, prd, position)
            }, 2500)

        } else if (position > 7 && position <= 9) {
            val rd = Random
            val nbA = (rd.nextInt(15) + 30)
            val nbB = rd.nextInt(100 - nbA)
            val nbC = rd.nextInt(100 - nbB - nbA)
            val nbD = 100 - nbA - nbB - nbC
            val prA = nbA.toFloat()
            val prB = nbB.toFloat()
            val prC = nbC.toFloat()
            val prD = nbD.toFloat()
            hander.postDelayed(Runnable {
                trueCase(tva, tvb, tvc, tvd, prA, prB, prC, prD, pra, prb, prc, prd, position)
            }, 2500)

        } else if (position > 9 && position <= 12) {
            val rd = Random
            val nbA = (rd.nextInt(10) + 30)
            val nbB = rd.nextInt(100 - nbA)
            val nbC = rd.nextInt(100 - nbB - nbA)
            val nbD = 100 - nbA - nbB - nbC
            val prA = nbA.toFloat()
            val prB = nbB.toFloat()
            val prC = nbC.toFloat()
            val prD = nbD.toFloat()
            hander.postDelayed(Runnable {
                trueCase(tva, tvb, tvc, tvd, prA, prB, prC, prD, pra, prb, prc, prd, position)
            }, 2500)

        } else if (position > 12) {
            val rd = Random
            val nbA = (rd.nextInt(5) + 30)
            val nbB = rd.nextInt(100 - nbA)
            val nbC = rd.nextInt(100 - nbB - nbA)
            val nbD = 100 - nbA - nbB - nbC
            val prA = nbA.toFloat()
            val prB = nbB.toFloat()
            val prC = nbC.toFloat()
            val prD = nbD.toFloat()
            hander.postDelayed(Runnable {
                trueCase(tva, tvb, tvc, tvd, prA, prB, prC, prD, pra, prb, prc, prd, position)
            }, 2500)

        }
    }

    fun trueCase(
        va: TextView,
        vb: TextView,
        vc: TextView,
        vd: TextView,
        nba: Float,
        nbb: Float,
        nbc: Float,
        nbd: Float,
        pa: ProgressView,
        pb: ProgressView,
        pc: ProgressView,
        pd: ProgressView,
        position: Int
    ) {
        if (arr_Question[position].isTrue == 1) {
            setProgress(pa, pb, pc, pd, nba, nbb, nbc, nbd)
            setAnswer(va, vb, vc, vd, nba, nbb, nbc, nbd)
        } else if (arr_Question[position].isTrue == 2) {
            setProgress(pa, pb, pc, pd, nbb, nba, nbc, nbd)
            setAnswer(va, vb, vc, vd, nbb, nba, nbc, nbd)
        } else if (arr_Question[position].isTrue == 3) {
            setProgress(pa, pb, pc, pd, nbb, nbc, nba, nbd)
            setAnswer(va, vb, vc, vd, nbb, nbc, nba, nbd)
        } else if (arr_Question[position].isTrue == 4) {
            setProgress(pa, pb, pc, pd, nbb, nbc, nbd, nba)
            setAnswer(va, vb, vc, vd, nbb, nbc, nbd, nba)
        }
    }

    fun setAnswer(
        va: TextView,
        vb: TextView,
        vc: TextView,
        vd: TextView,
        nba: Float,
        nbb: Float,
        nbc: Float,
        nbd: Float
    ) {
        va.setText("$nba %")
        vb.setText("$nbb %")
        vc.setText("$nbc %")
        vd.setText("$nbd %")
    }

    fun setProgress(
        pa: ProgressView,
        pb: ProgressView,
        pc: ProgressView,
        pd: ProgressView,
        nba: Float,
        nbb: Float,
        nbc: Float,
        nbd: Float
    ) {
        pa.progress = nba
        pb.progress = nbb
        pc.progress = nbc
        pd.progress = nbd

    }

    @SuppressLint("UseRequireInsteadOfGet")
    fun digmoney(position: Int) {
        var dig = Dialog(this!!.context!!)
        dig.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dig.setContentView(R.layout.digalognoti)
        dig.show()
        dig.setCancelable(false)
        if (position == 0) {
            dig.findViewById<TextView>(R.id.txtbonus)
                .setText("Chúc mừng bạn ra về tay trắng ")

        } else {
            dig.findViewById<TextView>(R.id.txtbonus)
                .setText("Chúc mừng bạn nhận được ${arr_Money[14 - position + 1].money}")
        }
        dig.findViewById<TextView>(R.id.txtconfirm).setOnClickListener {
            var namee = dig.findViewById<EditText>(R.id.edtname).text.toString().trim()
            if (namee.isEmpty()) {
                Toast.makeText(context, "Please enter name", Toast.LENGTH_SHORT).show()
            } else {
                if (position <= 15) {
                    if (position == 0) {
                        var int = Intent(context, MainActivity::class.java)
                        startActivity(int)
                        dig.dismiss()
                    } else {
                        GetSQLData(this!!.context!!).insertHight(
                            DataPerson(
                                "2",
                                namee,
                                arr_Money[14 - position + 1].money,
                                arr_Question[position - 1].number.toString()
                            )
                        )
                        var int = Intent(context, MainActivity::class.java)
                        startActivity(int)
                        dig.dismiss()
                    }
                }
            }
            cdown!!.cancel()

        }

    }

    private fun countDown(position: Int) {
        cdown = object : CountDownTimer(30000, 1000) {
            override fun onFinish() {
                digmoney(position)
                Toast.makeText(context, "timeout", Toast.LENGTH_SHORT).show()
            }

            override fun onTick(millisUntilFinished: Long) {
                binding!!.txtclock.setText((millisUntilFinished / 1000).toString())
            }

        }

    }

    override fun onDestroy() {
        Toast.makeText(context, "destroy3", Toast.LENGTH_SHORT).show()
        cdown!!.cancel()
        super.onDestroy()
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(context, "create3", Toast.LENGTH_SHORT).show()
    }

    override fun BackFr() {
        var int = Intent(context, MainActivity::class.java)
        startActivity(int)

        super.BackFr()
    }

}