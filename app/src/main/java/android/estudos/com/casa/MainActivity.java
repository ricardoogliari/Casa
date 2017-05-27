package android.estudos.com.casa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button btnCozinha;
    private Button btnSala;
    private Button btnGaragem;
    private Button btnDormitorio;
    private Button btnLadoDeForaGaragem;
    private Button btnLadoDeFora;

    private Retorno retorno;

    public static String ACTION_HOME = "ON_OFF";

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String acao = intent.getStringExtra("acao");
            int comodo = Integer.parseInt(""+acao.charAt(0));
            int onOff = Integer.parseInt(""+acao.charAt(1));

            switch (comodo){
                case 1:
                    if (onOff == 0){//desliga
                        if (retorno.luzes.cozinha){
                            retorno.luzes.cozinha = false;
                            atualiza();
                        }
                    } else {//liga
                        if (!retorno.luzes.cozinha){
                            retorno.luzes.cozinha = true;
                            atualiza();
                        }
                    }
                    break;
                case 2:
                    if (onOff == 0){//desliga
                        if (retorno.luzes.sala){
                            retorno.luzes.sala = false;
                            atualiza();
                        }
                    } else {//liga
                        if (!retorno.luzes.sala){
                            retorno.luzes.sala = true;
                            atualiza();
                        }
                    }
                    break;
                case 3:
                    if (onOff == 0){//desliga
                        if (retorno.luzes.garagem){
                            retorno.luzes.garagem = false;
                            atualiza();
                        }
                    } else {//liga
                        if (!retorno.luzes.garagem){
                            retorno.luzes.garagem = true;
                            atualiza();
                        }
                    }
                    break;
                case 4:
                    if (onOff == 0){//desliga
                        if (retorno.luzes.dormitorio){
                            retorno.luzes.dormitorio = false;
                            atualiza();
                        }
                    } else {//liga
                        if (!retorno.luzes.cozinha){
                            retorno.luzes.cozinha = true;
                            atualiza();
                        }
                    }
                    break;
                case 5:
                    if (onOff == 0){//desliga
                        if (retorno.luzes.foraLadoGaragem){
                            retorno.luzes.foraLadoGaragem = false;
                            atualiza();
                        }
                    } else {//liga
                        if (!retorno.luzes.foraLadoGaragem){
                            retorno.luzes.foraLadoGaragem = true;
                            atualiza();
                        }
                    }
                    break;
                case 6:
                    if (onOff == 0){//desliga
                        if (retorno.luzes.fora){
                            retorno.luzes.fora = false;
                            atualiza();
                        }
                    } else {//liga
                        if (!retorno.luzes.fora){
                            retorno.luzes.fora = true;
                            atualiza();
                        }
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCozinha = (Button) findViewById(R.id.btnCozinha);
        btnSala = (Button) findViewById(R.id.btnSala);
        btnGaragem = (Button) findViewById(R.id.btnGaragem);
        btnDormitorio = (Button) findViewById(R.id.btnDormitorio);
        btnLadoDeForaGaragem = (Button) findViewById(R.id.btnLadoDeForaGaragem);
        btnLadoDeFora = (Button) findViewById(R.id.btnLadoDeFora);
        //verde 1BF24D
        //vermelho D11818

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                retorno = dataSnapshot.getValue(Retorno.class);
                btnCozinha.setBackgroundColor(retorno.luzes.cozinha?Color.parseColor("#1BF24D"):Color.parseColor("#D11818"));
                btnSala.setBackgroundColor(retorno.luzes.sala?Color.parseColor("#1BF24D"):Color.parseColor("#D11818"));
                btnGaragem.setBackgroundColor(retorno.luzes.garagem?Color.parseColor("#1BF24D"):Color.parseColor("#D11818"));
                btnDormitorio.setBackgroundColor(retorno.luzes.dormitorio?Color.parseColor("#1BF24D"):Color.parseColor("#D11818"));
                btnLadoDeForaGaragem.setBackgroundColor(retorno.luzes.foraLadoGaragem?Color.parseColor("#1BF24D"):Color.parseColor("#D11818"));
                btnLadoDeFora.setBackgroundColor(retorno.luzes.fora?Color.parseColor("#1BF24D"):Color.parseColor("#D11818"));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("CASA", databaseError.getMessage());
            }
        };
        MyApplication.myRef.addValueEventListener(listener);

        IntentFilter filtro = new IntentFilter(ACTION_HOME);
        registerReceiver(receiver, filtro);
    }

    public void atualiza(){
        MyApplication.myRef.setValue(retorno);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public void ladoDeFora(View view) {
        retorno.luzes.foraLadoGaragem = !retorno.luzes.foraLadoGaragem;
        atualiza();
    }

    public void fora(View view) {
        retorno.luzes.fora = !retorno.luzes.fora;
        atualiza();
    }

    public void garagem(View view) {
        retorno.luzes.garagem = !retorno.luzes.garagem;
        atualiza();
    }

    public void dormitorio(View view) {
        retorno.luzes.dormitorio = !retorno.luzes.dormitorio;
        atualiza();
    }

    public void cozinha(View view) {
        retorno.luzes.cozinha = !retorno.luzes.cozinha;
        atualiza();
    }

    public void sala(View view) {
        retorno.luzes.sala = !retorno.luzes.sala;
        atualiza();
    }
}
