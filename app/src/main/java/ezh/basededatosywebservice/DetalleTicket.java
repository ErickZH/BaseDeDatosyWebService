package ezh.basededatosywebservice;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DetalleTicket extends AppCompatActivity
{
    TextView txtId;
    TextView txtDescripcion;
    TextView txtNombreUsuario;
    TextView txtNombreDepto;
    TextView txtNombreProceso;
    TextView txtRol;
    TextView txtFecha;
    TextView txtStatus;
    TextView txtFechaResolucion;
    TextView txtNombreLinea;
    TextView txtNombreEquipo;

    SQLHelper sqlHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_ticket);

        // Mapeamos xml con Java
        txtId = (TextView) findViewById(R.id.textId);
        txtDescripcion = (TextView) findViewById(R.id.textDescripcion);
        txtNombreUsuario = (TextView) findViewById(R.id.nombreUsuario);
        txtNombreDepto = (TextView) findViewById(R.id.nombreDepto);
        txtNombreProceso = (TextView)findViewById(R.id.nombreproceso);
        txtRol = (TextView) findViewById(R.id.nombreRol);
        txtFecha = (TextView) findViewById(R.id.fecha);
        txtStatus = (TextView) findViewById(R.id.status);
        txtFechaResolucion = (TextView) findViewById(R.id.fechaResolucion);
        txtNombreLinea = (TextView) findViewById(R.id.nombreLinea);
        txtNombreEquipo = (TextView) findViewById(R.id.nombreEquipo);

        // Obtenemos el id del ticket pasado por el Intent
        String idTicket = (String)getIntent().getExtras().getSerializable("ticket");

        // Consultamos el registro correspondiente al idTicket
        sqlHelper = new SQLHelper(this);
        db = sqlHelper.getWritableDatabase();

        // Consulta
        String consulta_ticket = "SELECT IdTicket, Descripcion, NombreCompleto, NombreDepartamento, " +
                "NombreProceso, NombreRol,  Fecha, Estatus, FechaResolucion,  LineaNombre, EquipoNombre" +
                " FROM ticket WHERE IdTicket = '"+idTicket+"'";

        // Obtenemos un Curso apartir de la consulta
        Cursor cursor = db.rawQuery(consulta_ticket, null);

        // Validamos que el Curso no este vacio
        if (cursor.moveToFirst())
        {

            // Obtenemos los datos de la consulta
            txtId.setText("Ticket Id: "+ cursor.getString(0));
            txtDescripcion.setText("Descripción: "+cursor.getString(1));
            txtNombreUsuario.setText("Nombre usuario: "+cursor.getString(2));
            txtNombreDepto.setText("Nombre departamento: "+ cursor.getString(3));
            txtNombreProceso.setText("Nombre proceso: "+cursor.getString(4));
            txtRol.setText("Nombre rol: "+cursor.getString(5));
            txtFecha.setText("Fecha: "+cursor.getString(6));
            txtStatus.setText("Estatus: "+cursor.getString(7));
            txtFechaResolucion.setText("Fecha resolución: "+cursor.getString(8));
            txtNombreLinea.setText("Nombre linea: "+cursor.getString(9));
            txtNombreEquipo.setText("Nombre equipo: "+cursor.getString(10));

        }
        else
        {
            Toast.makeText(getApplicationContext(),"No se pudo obtener la información del Ticket "+idTicket,Toast.LENGTH_SHORT).show();
        }
    }
}
