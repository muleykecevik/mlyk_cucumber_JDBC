package stepDefinitions;

import io.cucumber.java.en.Given;
import utilities.JDBCReusableMethods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static utilities.JDBCReusableMethods.getStatement;

public class StepDefinitions {
    String query;
    ResultSet rs;
    Statement st;
    String query1;
    String query2;

    int flag;


    @Given("Database baglantisi kurulur.")
    public void database_baglantisi_kurulur() {
        JDBCReusableMethods.createConnection();
    }

    @Given("Query hazirlanir.")
    public void query_hazirlanir() {
        query= "Select charge_id From heallife_hospitaltraining.ambulance_call Where patient_id=1 AND driver='Smith';";
    }

    @Given("Query calistirilir ve sonuclari alinir.")
    public void query_calistirilir_ve_sonuclari_alinir() throws SQLException {

        rs = getStatement().executeQuery(query);

    }
    @Given("Query sonuclari dogrulanir")
    public void query_sonuclari_dogrulanir() throws SQLException {
        int expectedData = 2;

        flag=0;
        while(rs.next()){
            flag++;
        }
        assertEquals(expectedData,flag);
    }
    @Given("Database baglantisi kapatilir")
    public void database_baglantisi_kapatilir() {

        JDBCReusableMethods.closeConnection();
    }

    @Given("Update Query'si hazirlanir.")
    public void update_query_si_hazirlanir() throws SQLException {
       query1="insert into heallife_hospitaltraining.appointment (priority,specialist,doctor,amount,message,appointment_status,source,is_opd,is_ipd,live_consult) values (1,2,2,0,'hello','approved','OFFline','no','yes','yes')";

    }

    @Given("Query sonuclari alinir ve dogrulanir")
    public void query_sonuclari_alinir_ve_dogrulanir() throws SQLException {
       int sonuc= JDBCReusableMethods.getStatement().executeUpdate(query1);

       int verify=0;
       if (sonuc>0){
           verify++;
       }
       assertEquals(verify,1);

    }

    @Given("Besinci Query hazirlanir")
    public void besinci_query_hazirlanir() {
        query2=
    }
    @Given("Besinci Query sonuclari dogrulanir")
    public void besinci_query_sonuclari_dogrulanir() {

    }




}


