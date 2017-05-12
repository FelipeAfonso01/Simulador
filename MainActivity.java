package com.example.aluno.simulador;

import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.IntegerRes;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.math.RoundingMode;
import java.util.Locale;

import static android.R.attr.format;

public class MainActivity extends AppCompatActivity {
    private EditText txtvalor;
    private EditText txtentrada;
    private EditText txtmeses;
    private EditText txtjuros;
    private TextView tvretorno;
    private TextView tvretorno1;
    private TextView tvretorno2;
    private TextView tvretorno3;
    private TextView tvretorno4;
    private double valor;
    private double entrada;
    private Integer meses;
    private double juros;
    double conta_valor;
    double conta_entrada;
    double conta_taxa;
    double conta;
    double parcela;
    double total_geral;
    double total_geral1;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtvalor = (EditText) findViewById(R.id.txtvalor);
        txtentrada = (EditText) findViewById(R.id.txtentrada);
        txtmeses = (EditText) findViewById(R.id.txtmeses);
        txtjuros = (EditText) findViewById(R.id.txtjuros);
        tvretorno = (TextView) findViewById(R.id.tvretorno);
        tvretorno1 = (TextView) findViewById(R.id.tvretorno1);
        tvretorno2 = (TextView) findViewById(R.id.tvretorno2);
        tvretorno3 = (TextView) findViewById(R.id.tvretorno3);
        tvretorno4 = (TextView) findViewById(R.id.tvretorno4);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void calcula(View v) {
        valor = Double.parseDouble(txtvalor.getText().toString());
        entrada = Double.parseDouble(txtentrada.getText().toString());
        meses = Integer.parseInt(txtmeses.getText().toString());
        juros = Double.parseDouble(txtjuros.getText().toString());

        conta_valor = valor - entrada;
        conta_entrada = valor - conta_valor;
        conta_taxa = juros / 100;
        conta = Math.pow((1 + conta_taxa), meses);
        conta = (1 / conta);
        conta = (1 - conta);
        conta = (conta_taxa / conta);
        parcela = (conta_valor * conta);
        total_geral = parcela * meses;
        total_geral1 = conta_entrada + (parcela * meses);


        total_geral1=Double.valueOf(String.format(Locale.US,"%.2f", total_geral1));
        total_geral=Double.valueOf(String.format(Locale.US,"%.2f", total_geral));
        valor=Double.valueOf(String.format(Locale.US,"%.2f", valor));
        entrada=Double.valueOf(String.format(Locale.US,"%.2f", entrada));
        juros=Double.valueOf(String.format(Locale.US,"%.2f", juros));
        parcela=Double.valueOf(String.format(Locale.US,"%.2f", parcela));


        tvretorno.setText("Entrada de R$" + entrada + "");
        tvretorno1.setText("+" + meses + " parcelas de R$" + parcela + "");
        tvretorno2.setText("Á uma taxa de " + juros + "% a.m, ");
        tvretorno3.setText("valor da parcela será de R$" + total_geral + "");
        tvretorno4.setText(" Valor total será de R$" + total_geral1 + "");


    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
