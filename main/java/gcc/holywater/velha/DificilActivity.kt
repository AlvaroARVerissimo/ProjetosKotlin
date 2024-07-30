package gcc.holywater.velha

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import gcc.holywater.velha.databinding.ActivityDificilBinding
import kotlin.random.Random

class DificilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDificilBinding
    private val tabuleiro = Array(4) { Array(4) { "" } }

    // Qual jogador está jogando
    private var jogadorAtual = "X"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDificilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        // Associar o click listener aos botões
        setButtonClickListeners()
    }
    private fun setButtonClickListeners() {
        binding.buttonZero.setOnClickListener { buttonClick(it) }
        binding.buttonUm.setOnClickListener { buttonClick(it) }
        binding.buttonDois.setOnClickListener { buttonClick(it) }
        binding.buttonTres.setOnClickListener { buttonClick(it) }
        binding.buttonQuatro.setOnClickListener { buttonClick(it) }
        binding.buttonCinco.setOnClickListener { buttonClick(it) }
        binding.buttonSeis.setOnClickListener { buttonClick(it) }
        binding.buttonSete.setOnClickListener { buttonClick(it) }
        binding.buttonOito.setOnClickListener { buttonClick(it) }
        binding.buttonNove.setOnClickListener { buttonClick(it) }
        binding.buttonDez.setOnClickListener { buttonClick(it) }
        binding.buttonOnze.setOnClickListener { buttonClick(it) }
        binding.buttonDoze.setOnClickListener { buttonClick(it) }
        binding.buttonTreze.setOnClickListener { buttonClick(it) }
        binding.buttonQuatorze.setOnClickListener { buttonClick(it) }
        binding.buttonQuinze.setOnClickListener { buttonClick(it) }
    }

    private fun buttonClick(view: View) {
        val buttonSelecionado = view as Button

        when (buttonSelecionado.id) {
            binding.buttonZero.id -> tabuleiro[0][0] = jogadorAtual
            binding.buttonUm.id -> tabuleiro[0][1] = jogadorAtual
            binding.buttonDois.id -> tabuleiro[0][2] = jogadorAtual
            binding.buttonTres.id -> tabuleiro[0][3] = jogadorAtual
            binding.buttonQuatro.id -> tabuleiro[1][0] = jogadorAtual
            binding.buttonCinco.id -> tabuleiro[1][1] = jogadorAtual
            binding.buttonSeis.id -> tabuleiro[1][2] = jogadorAtual
            binding.buttonSete.id -> tabuleiro[1][3] = jogadorAtual
            binding.buttonOito.id -> tabuleiro[2][0] = jogadorAtual
            binding.buttonNove.id -> tabuleiro[2][1] = jogadorAtual
            binding.buttonDez.id -> tabuleiro[2][2] = jogadorAtual
            binding.buttonOnze.id -> tabuleiro[2][3] = jogadorAtual
            binding.buttonDoze.id -> tabuleiro[3][0] = jogadorAtual
            binding.buttonTreze.id -> tabuleiro[3][1] = jogadorAtual
            binding.buttonQuatorze.id -> tabuleiro[3][2] = jogadorAtual
            binding.buttonQuinze.id -> tabuleiro[3][3] = jogadorAtual
        }

        buttonSelecionado.setBackgroundColor(Color.BLUE)
        buttonSelecionado.isEnabled = false

        var vencedor = verificaVencedor(tabuleiro)
        if (!vencedor.isNullOrBlank()) {
            Toast.makeText(this, "Vencedor: $vencedor", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            jogadaComputador()
            vencedor = verificaVencedor(tabuleiro)
            if (!vencedor.isNullOrBlank()) {
                Toast.makeText(this, "Vencedor: $vencedor", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun jogadaComputador() {
        var rX: Int
        var rY: Int
        var i = 0

        do {
            rX = Random.nextInt(0, 4)
            rY = Random.nextInt(0, 4)
            i++
        } while ((tabuleiro[rX][rY] == "X" || tabuleiro[rX][rY] == "O") && i < 16)

        tabuleiro[rX][rY] = "O"

        val button = when (rX * 4 + rY) {
            0 -> binding.buttonZero
            1 -> binding.buttonUm
            2 -> binding.buttonDois
            3 -> binding.buttonTres
            4 -> binding.buttonQuatro
            5 -> binding.buttonCinco
            6 -> binding.buttonSeis
            7 -> binding.buttonSete
            8 -> binding.buttonOito
            9 -> binding.buttonNove
            10 -> binding.buttonDez
            11 -> binding.buttonOnze
            12 -> binding.buttonDoze
            13 -> binding.buttonTreze
            14 -> binding.buttonQuatorze
            15 -> binding.buttonQuinze
            else -> null
        }

        button?.apply {
            setBackgroundColor(Color.RED)
            isEnabled = false
        }
    }

    private fun verificaVencedor(tabuleiro: Array<Array<String>>): String? {
        // Verifica linhas e colunas
        for (i in 0 until 4) {
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2] && tabuleiro[i][2] == tabuleiro[i][3] && tabuleiro[i][0].isNotEmpty()) {
                return tabuleiro[i][0]
            }
            if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i] && tabuleiro[2][i] == tabuleiro[3][i] && tabuleiro[0][i].isNotEmpty()) {
                return tabuleiro[0][i]
            }
        }
        // Verifica diagonais
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2] && tabuleiro[2][2] == tabuleiro[3][3] && tabuleiro[0][0].isNotEmpty()) {
            return tabuleiro[0][0]
        }
        if (tabuleiro[0][3] == tabuleiro[1][2] && tabuleiro[1][2] == tabuleiro[2][1] && tabuleiro[2][1] == tabuleiro[3][0] && tabuleiro[0][3].isNotEmpty()) {
            return tabuleiro[0][3]
        }
        // Verifica empate
        return if (tabuleiro.all { row -> row.all { it.isNotEmpty() } }) {
            "Empate"
        } else {
            null
        }
    }
}
