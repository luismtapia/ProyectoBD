package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQL {
    public static Connection Conexion;
    private final String usuario = "fernanda";
    private final String usuariogerente = "luis";
    private final String usuarioempleado = "empleado1";
    private final String contraseña = "123";
    private final String dbnombre = "FERNANDA";
    
    public void Conectar(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//CARGA EL DRIVER
            String connectionUrl = "jdbc:sqlserver://TaRo:1433;databaseName=FERNANDA";
            Conexion = DriverManager.getConnection(connectionUrl,usuario,contraseña);
            
            System.out.println("Conectado.");
            System.out.println("Se ha iniciado la conexión con el servidor de forma exitosa");
        }
        catch (ClassNotFoundException | SQLException ex){
            System.out.println("Error.");
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeConnection() {
        try {
            Conexion.close();
            System.out.println("Se ha finalizado la conexión con el servidor");
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//----------------------------------------------------------------------------------------------------------------------------//    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
    public ResultSet getUltimoIdFactura(){
        String query="SELECT MAX(InvoiceId) FROM invoice";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    
    // LECTURA
    public ObservableList<Empleado> getEmpleados() {
        ObservableList<Empleado> listEmployees= FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM employee";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                listEmployees.add(new Empleado(
                        rs.getInt("EmployeeId"),
                    rs.getString("LastName"),
                    rs.getString("FirstName"),
                    rs.getString("Title"),
                    rs.getInt("ReportsTo"),
                    rs.getDate("BirthDate"),
                    rs.getDate("HireDate"),
                    rs.getString("Address"),
                    rs.getString("City"),
                    rs.getString("State"),
                    rs.getString("Country"),
                    rs.getString("PostalCode"),
                    rs.getString("Phone"),
                    rs.getString("Fax"),
                    rs.getString("Email")));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return listEmployees;
    }
   
    public ObservableList<Cliente> getClientes() {
        ObservableList<Cliente> listcustomer= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM customer";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                listcustomer.add(new Cliente(
                    rs.getInt("CustomerId"),
                    rs.getString("FirstName"),    
                    rs.getString("LastName"),
                    rs.getString("Company"),
                    rs.getString("Address"),
                    rs.getString("City"),
                    rs.getString("State"),
                    rs.getString("Country"),
                    rs.getString("PostalCode"),
                    rs.getString("Phone"),
                    rs.getString("Fax"),
                    rs.getString("Email"),
                    rs.getInt("SupportRepId")));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return listcustomer;
    }
    
    public ObservableList<Artista> getArtistas() {
        ObservableList<Artista> listartista= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM artist";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                listartista.add(new Artista(
                    rs.getInt("ArtistId"),
                    rs.getString("Name")));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return listartista;
    }
    
    public ObservableList<Album> getAlbum() {
        ObservableList<Album> listalbum= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM album";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                listalbum.add(new Album(
                    rs.getInt("AlbumId"),
                    rs.getString("Title"),
                    rs.getInt("ArtistId")));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return listalbum;
    }
    
    public ObservableList<Genero> getGenero() {
        ObservableList<Genero> listgenre= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM genre";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                listgenre.add(new Genero(
                    rs.getInt("GenreId"),
                    rs.getString("Name")));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return listgenre;
    }
    
    public ObservableList<Pistax> getPistas() {
        ObservableList<Pistax> listtrack= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM track";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                listtrack.add(new Pistax(
                    rs.getInt("TrackId"),
                    rs.getString("Name"),
                    rs.getInt("AlbumId"),
                    rs.getInt("MediaTypeId"),
                    rs.getInt("GenreId"),
                    rs.getString("Composer"),
                    rs.getInt("Milliseconds"),
                    rs.getInt("Bytes"),
                    rs.getDouble("UnitPrice")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return listtrack;
    }
    
    public ObservableList<Pista> getPistasJesica() {
        ObservableList<Pista> listtrack= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM track";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                listtrack.add(new Pista(
                    rs.getInt("TrackId"),
                    rs.getString("Name"),
                    rs.getInt("AlbumId"),
                    rs.getInt("MediaTypeId"),
                    rs.getInt("GenreId"),
                    rs.getString("Composer"),
                    rs.getInt("Milliseconds"),
                    rs.getInt("Bytes"),
                    rs.getDouble("UnitPrice")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return listtrack;
    }
    
    public ObservableList<Pista> getPistasPorGeneroJesica(Integer idgenero) {
        ObservableList<Pista> listtrack= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM track WHERE GenreId = '"+idgenero+"'";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                listtrack.add(new Pista(
                    rs.getInt("TrackId"),
                    rs.getString("Name"),
                    rs.getInt("AlbumId"),
                    rs.getInt("MediaTypeId"),
                    rs.getInt("GenreId"),
                    rs.getString("Composer"),
                    rs.getInt("Milliseconds"),
                    rs.getInt("Bytes"),
                    rs.getDouble("UnitPrice")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return listtrack;
    }
    
    public ObservableList<Pista> getPistasPorAlbumJesica(Integer idalbum) {
        ObservableList<Pista> listtrack= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM track WHERE AlbumId = '"+idalbum+"'";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                listtrack.add(new Pista(
                    rs.getInt("TrackId"),
                    rs.getString("Name"),
                    rs.getInt("AlbumId"),
                    rs.getInt("MediaTypeId"),
                    rs.getInt("GenreId"),
                    rs.getString("Composer"),
                    rs.getInt("Milliseconds"),
                    rs.getInt("Bytes"),
                    rs.getDouble("UnitPrice")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return listtrack;
    }
    
    public ObservableList<Factura> getFactura() {
        ObservableList<Factura> listtrack= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM invoice";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                listtrack.add(new Factura(
                    rs.getInt("InvoiceId"),
                    rs.getInt("CustomerId"),
                    rs.getDate("InvoiceDate"),
                    rs.getString("BillingAddress"),
                    rs.getString("BillingCity"),
                    rs.getString("BillingState"),
                    rs.getString("BillingCountry"),
                    rs.getString("BillingPostalCode"),
                    rs.getDouble("Total")));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return listtrack;
    }
    
    
    
    
    public ObservableList<Pistax> getPistasdPorId(Integer _trackid) {
        ObservableList<Pistax> listtrack= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM track WHERE TrackId = '"+_trackid+"'";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                listtrack.add(new Pistax(
                    rs.getInt("TrackId"),
                    rs.getString("Name"),
                    rs.getInt("AlbumId"),
                    rs.getInt("MediaTypeId"),
                    rs.getInt("GenreId"),
                    rs.getString("Composer"),
                    rs.getInt("Milliseconds"),
                    rs.getInt("Bytes"),
                    rs.getDouble("UnitPrice")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return listtrack;
    }
    
    public ObservableList<Pistax> getPistasPorArtista(String artista) {
        ObservableList<Pistax> listtrack= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM track WHERE Composer = '"+artista+"'";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                listtrack.add(new Pistax(
                    rs.getInt("TrackId"),
                    rs.getString("Name"),
                    rs.getInt("AlbumId"),
                    rs.getInt("MediaTypeId"),
                    rs.getInt("GenreId"),
                    rs.getString("Composer"),
                    rs.getInt("Milliseconds"),
                    rs.getInt("Bytes"),
                    rs.getDouble("UnitPrice")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return listtrack;
    }
    
    public ObservableList<Pistax> getPistasPorGenero(Integer idgenero) {
        ObservableList<Pistax> listtrack= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM track WHERE GenreId = '"+idgenero+"'";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                listtrack.add(new Pistax(
                    rs.getInt("TrackId"),
                    rs.getString("Name"),
                    rs.getInt("AlbumId"),
                    rs.getInt("MediaTypeId"),
                    rs.getInt("GenreId"),
                    rs.getString("Composer"),
                    rs.getInt("Milliseconds"),
                    rs.getInt("Bytes"),
                    rs.getDouble("UnitPrice")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return listtrack;
    }
    
    public ObservableList<Pistax> getPistasPorAlbum(Integer idalbum) {
        ObservableList<Pistax> listtrack= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM track WHERE AlbumId = '"+idalbum+"'";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                listtrack.add(new Pistax(
                    rs.getInt("TrackId"),
                    rs.getString("Name"),
                    rs.getInt("AlbumId"),
                    rs.getInt("MediaTypeId"),
                    rs.getInt("GenreId"),
                    rs.getString("Composer"),
                    rs.getInt("Milliseconds"),
                    rs.getInt("Bytes"),
                    rs.getDouble("UnitPrice")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return listtrack;
    }
    
    //INSERTAR
    public Boolean insertaEmpleado(Empleado nuevoempleado){
        try{
        String query="INSERT INTO employee (LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setString(1, nuevoempleado.getLastName());
        st.setString(2, nuevoempleado.getFirstName());
        st.setString(3, nuevoempleado.getTitle());
        st.setInt(4, nuevoempleado.getReportsTo());
        st.setDate(5, nuevoempleado.getBirthDate());
        st.setDate(6, nuevoempleado.getHireDate());
        st.setString(7, nuevoempleado.getAddress());
        st.setString(8, nuevoempleado.getCity());
        st.setString(9, nuevoempleado.getState());
        st.setString(10, nuevoempleado.getCountry());
        st.setString(11, nuevoempleado.getPostalCode());
        st.setString(12, nuevoempleado.getPhone());
        st.setString(13, nuevoempleado.getFax());
        st.setString(14, nuevoempleado.getEmail());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }   
    
    public Boolean insertaCliente(Cliente nuevocliente){
        try{
        String query="insert into customer (FirstName, LastName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email, SupportRepId) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setString(1, nuevocliente.getFirstName());
        st.setString(2, nuevocliente.getLastName());
        st.setString(3, nuevocliente.getCompany());
        st.setString(4, nuevocliente.getAddress());
        st.setString(5, nuevocliente.getCity());
        st.setString(6, nuevocliente.getState());
        st.setString(7, nuevocliente.getCountry());
        st.setString(8, nuevocliente.getPostalCode());
        st.setString(9, nuevocliente.getPhone());
        st.setString(10, nuevocliente.getFax());
        st.setString(11, nuevocliente.getEmail());
        st.setInt(12, nuevocliente.getSupportRepId());
        
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaArtista(Artista nuevoartista){
        try{
        String query="insert into artist (Name) values(?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setString(1, nuevoartista.getNameArtista());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaAlbum(Album nuevoalbum){
        try{
        String query="insert into album (Title , ArtistId) values(? , ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setString(1, nuevoalbum.getTitAlbum());
        st.setInt(2, nuevoalbum.getArtistaId());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaGenero(Genero nuevogenero){
        try{
        String query="insert into genre (Name) values(?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setString(1, nuevogenero.getName());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    public Boolean insertaFactura(Factura nuevafactura){
        try{
        String query="INSERT INTO invoice (CustomerId, InvoiceDate, BillingAddress, BillingCity, BillingState, BillingCountry, BillingPostalCode, Total) values(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setInt(1, nuevafactura.getCustomerId());
        st.setDate(2, nuevafactura.getInvoiceDate());
        st.setString(3, nuevafactura.getBillingAddress());
        st.setString(4, nuevafactura.getBillingCity());
        st.setString(5, nuevafactura.getBillingState());
        st.setString(6, nuevafactura.getBillingCountry());
        st.setString(7, nuevafactura.getBillingPostalCode());
        st.setDouble(8, nuevafactura.getTotal());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;
    }
    
    public Boolean insertaLineaFactura(LineaFactura nuevalineafactura){
        try{
        String query="INSERT INTO invoiceline (InvoiceId, TrackId, UnitPrice, Quantity) values(?, ?, ?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setInt(1, nuevalineafactura.getInvoiceId());
        st.setInt(2, nuevalineafactura.getTrackId());
        st.setDouble(3, nuevalineafactura.getUnitPrice());
        st.setInt(4, nuevalineafactura.getCantidad());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;
    }
    
    //UPDATE
    public boolean ActualizaEmpleado(Integer Id_Empleado, String _LastName, String _FirstName, String _Title, int _ReportsTo, Date _BirthDate, Date _HireDate, String _Address,
                String _City, String _State, String _Country, String _PostalCode, String _Phone, String _Fax, String _Email){
        //update employee set *****="fecha" where id=?
        try
        {
            String query ="UPDATE employee SET"
                    + " LastName = ?, "//1
                    + "FirstName= ?, "//2
                    + "Title=?, "//3
                    + "ReportsTo=?, "//4
                    + "BirthDate= ?, "//5
                    + "HireDate=?, "//6
                    + "Address=?,"//7
                    + "City=?,"//8
                    + "State=?,"//9
                    + "Country=?,"//10
                    + "PostalCode=?,"//11
                    + "Phone=?,"//12
                    + "Fax=?,"//13
                    + "Email=?"//14
                    + " WHERE EmployeeId = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setString(1, _LastName);
            st.setString(2, _FirstName);
            st.setString(3, _Title);
            st.setInt(4, _ReportsTo);
            st.setDate(5, _BirthDate);
            st.setDate(6, _HireDate);
            st.setString(7, _Address);
            st.setString(8, _City);
            st.setString(9, _State);
            st.setString(10, _Country);
            st.setString(11, _PostalCode);
            st.setString(12, _Phone);
            st.setString(13, _Fax);
            st.setString(14, _Email);
            st.setInt(15, Id_Empleado);
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean ActualizaCliente(Integer Id_Cliente, String _LastName, String _FirstName, String _Company, String _Address,
                String _City, String _State, String _Country, String _PostalCode, String _Phone, String _Fax, String _Email, int _SupportRepId){
        try
        {
            String query ="UPDATE customer SET"
                    + " LastName = ?, "//1
                    + "FirstName = ?, "//2
                    + "Company = ?, "//3
                    + "Address = ?,"//4
                    + "City = ?,"//5
                    + "State = ?,"//6
                    + "Country = ?,"//7
                    + "PostalCode=?,"//8
                    + "Phone=?,"//9
                    + "Fax=?,"//10
                    + "Email=?,"//11
                    + "SupportRepId = ? "//12
                    + " WHERE CustomerId = ?";//13
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setString(1, _LastName);
            st.setString(2, _FirstName);
            st.setString(3, _Company);
            st.setString(4, _Address);
            st.setString(5, _City);
            st.setString(6, _State);
            st.setString(7, _Country);
            st.setString(8, _PostalCode);
            st.setString(9, _Phone);
            st.setString(10, _Fax);
            st.setString(11, _Email);
            st.setInt(12, _SupportRepId);
            st.setInt(13, Id_Cliente);
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean ActualizaArtista(Integer _ArtistId, String _Name){
        try{
            String query ="UPDATE artist SET Name = ? WHERE ArtistId = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setString(1, _Name);
            st.setInt(2, _ArtistId);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    public boolean ActualizaAlbum(Integer _AlbumId, String _Title, Integer _ArtistId){
        try{
            String query ="UPDATE album SET Title = ?, ArtistId = ? WHERE AlbumId = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setString(1, _Title);
            st.setInt(2, _ArtistId);
            st.setInt(3, _AlbumId);
            st.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    public boolean ActualizaGenero(Integer _GenreId, String _Name){
        //update employee set *****="fecha" where id=?
        try
        {
            String query ="UPDATE genre SET Name = ? WHERE GenreId = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setString(1, _Name);//esto no es asi??? para que sirve
            st.setInt(2, _GenreId);
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }
    
    
    
    //ELIMINAR
    public boolean eliminaEmpleado(Integer Id_Empleado){
        try
        {
            String query ="DELETE FROM employee WHERE EmployeeId = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, Id_Empleado); ///fjgdfhshdjgsmhssssssjgggggggggggsh
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaCliente(Integer Id_Cliente){
        try
        {
            String query ="DELETE FROM customer WHERE CustomerId = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, Id_Cliente); ///fjgdfhshdjgsmhssssssjgggggggggggsh
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaArtista(Integer Id_Artista){
        try
        {
            String query ="DELETE FROM artist WHERE ArtistId = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, Id_Artista); ///fjgdfhshdjgsmhssssssjgggggggggggsh
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaAlbum(Integer Id_Album){
        try
        {
            String query ="DELETE FROM album WHERE AlbumId = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, Id_Album); ///fjgdfhshdjgsmhssssssjgggggggggggsh
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaGenero(Integer Id_Genero){
        try
        {
            String query ="DELETE FROM genre WHERE GenreId = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, Id_Genero); ///fjgdfhshdjgsmhssssssjgggggggggggsh
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    
    
    //BUSQUEDA POR NOMBRE
    public ResultSet getEmpleadoPorNombre(String _LastName){
        String query="SELECT * FROM employee WHERE LastName = '"+_LastName+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getClientePorNombre(String _LastName){//de artista
        String query="SELECT * FROM customer WHERE LastName = '"+_LastName+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getArtistaPorNombre(String _Name){//de artista
        String query="SELECT * FROM artist WHERE Name = '"+_Name+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getGeneroPorNombre(String _Name){
        String query="SELECT * FROM Genre WHERE Name = '"+_Name+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecuta"+e);
        }
        return rs;
    }
    
    public ResultSet getAlbumPorNombre(String _Name){
        String query="SELECT * FROM album WHERE Title = '"+_Name+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecuta"+e);
        }
        return rs;
    }
    
    public ResultSet getTiposMedioPorNombre(String _Name){
        String query="SELECT * FROM mediatype WHERE Name = '"+_Name+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecuta"+e);
        }
        return rs;
    }
    
    public ResultSet getFacturaPorPais(String _FacturaId){
        String query="SELECT * FROM invoice WHERE BillingCountry = '"+_FacturaId+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    //BUSQUEDA POR ID
    public ResultSet getEmpleadoPorId(Integer _EmployeeId){
        String query="SELECT * FROM employee WHERE EmployeeId = '"+_EmployeeId+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getClientePorId(Integer _CustomerId){
        String query="SELECT * FROM customer WHERE CustomerId = '"+_CustomerId+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getArtistaPorId(Integer _ArtitsId){//de artista
        String query="SELECT * FROM artist WHERE ArtistId = '"+_ArtitsId+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getAlbumPorId(Integer _AlbumId){//de artista
        String query="SELECT * FROM album WHERE AlbumId = '"+_AlbumId+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getGeneroPorId(Integer _GeneroId){
        String query=" SELECT * FROM genre WHERE GenreId = '"+ _GeneroId+"'";
        ResultSet rs=null;
        try {
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return rs;
    }
    
    public ResultSet getFacturaPorId(Integer _FacturaId){
        String query="SELECT * FROM invoice WHERE InvoiceId = '"+_FacturaId+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getLineaFacturaPorId(Integer _invoiceId){
        String query="SELECT * FROM invoiceline WHERE InvoiceId = '"+_invoiceId+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ResultSet getPistaPorId(Integer _PistasId){
        String query=" SELECT * FROM track WHERE TrackId = '"+ _PistasId+"'";
        ResultSet rs=null;
        try {
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return rs;
    }
    
    public ResultSet getMediaTypePorId(Integer _MediaTypeId){
        String query=" SELECT * FROM mediatype WHERE MediaTypeId = '"+ _MediaTypeId+"'";
        ResultSet rs=null;
        try {
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return rs;
    }
    
    
    
    public void imprimeEmpleados() {
        try {
            String query = "SELECT * FROM employee";
            Statement st = Conexion.createStatement();
            java.sql.ResultSet rs;
            rs = st.executeQuery(query);
 
            while (rs.next()) {
                System.out.println("id: " + rs.getString("EmployeeId") + " "
                        + "\nombre: " + rs.getString("LastName") + " "
                        + rs.getString("FirstName") + " "
                        + "\ntitulo: " + rs.getString("Title") + " "
                        + "\nreportto: " + rs.getString("ReportsTo") + " "
                        + "\nfecha: " + rs.getString("BirthDate") + " "
                        + "\nfecha: " + rs.getString("HireDate") + " "
                        + "\ndireccion: " + rs.getString("Address") + " "
                        + "\nciudad: " + rs.getString("City") + " "
                        + "\nestado: " + rs.getString("State") + " "
                        + "\npais: " + rs.getString("Country") + " "
                        + "\ncodigo postal: " + rs.getString("PostalCode") + " "
                        + "\ntelefono: " + rs.getString("Phone") + " "
                        + "\nfax: " + rs.getString("Fax") + " "
                        + "\nE-mail: " + rs.getString("Email") + "\n\n ");
            }
 
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
    }
    
    
        
    public ResultSet buscarEmpleadoPorIdDeEmpleado(Integer IdEmpleado){// en este metodo se realiza la busqueda del empleado que se ha seleccionado en el combobox o listview para despues mostrar su informacion
          String query ="SELECT * FROM employee where EmployeeId ='"+IdEmpleado+"'";
          ResultSet resultado=null;
          try
          {
          Statement st =Conexion.createStatement();
          resultado=st.executeQuery(query);
          }catch(Exception e)
          {
              System.out.println("Error al ejecutar consulta.");
              e.printStackTrace();
          }
          return resultado;
        }
    
    //LEER TODOS
    public ResultSet LeerEmpleados() {
            String query = "SELECT * FROM employee";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
            return resultado;
    }
    
    public ResultSet LeerClientes() {
            String query = "SELECT * FROM customer";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
            return resultado;
    }
    
    public ResultSet LeerArtistas() {
            String query = "SELECT * FROM artist";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
            return resultado;
    }
    
    public ResultSet LeerAlbums() {
            String query = "SELECT * FROM album";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
            return resultado;
    }
    
    public ResultSet LeerPistas() {
            String query = "SELECT * FROM track";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
            return resultado;
    }
    
    public ResultSet LeerCompras() {
            String query = "SELECT * FROM invoice";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
            return resultado;
    }
    
    public ResultSet LeerGeneros() {
            String query = "SELECT * FROM genre";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
            return resultado;
    }
    
    public ResultSet LeerFactura() {
            String query = "SELECT * FROM invoice";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
            return resultado;
    }
    
    
    public ResultSet ContarPaises() {
            String query = "SELECT COUNT(*) FROM invoice";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
            return resultado;
    }
    
    
    
   
    
    
    public ResultSet LeerTiposMedios() {
            String query = "SELECT * FROM mediatype";
            java.sql.ResultSet resultado=null;
            try {
                Statement st = Conexion.createStatement();
                resultado = st.executeQuery(query);
            } catch (Exception e) {
                System.out.println(e);
            }
            return resultado;
    }
    
    
    
    
    public ObservableList<Pista> getPistasTiposMedio(Integer MediaTypeId) {
        ObservableList<Pista> listtrack= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM track WHERE MediaTypeId = '"+MediaTypeId+"'";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                listtrack.add(new Pista(
                    rs.getInt("TrackId"),
                    rs.getString("Name"),
                    rs.getInt("AlbumId"),
                    rs.getInt("MediaTypeId"),
                    rs.getInt("GenreId"),
                    rs.getString("Composer"),
                    rs.getInt("Milliseconds"),
                    rs.getInt("Bytes"),
                    rs.getDouble("UnitPrice")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return listtrack;
    }
  public ObservableList<Tipo_medio> getTipoMedios() {
        ObservableList<Tipo_medio> listcustomer= FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM mediatype";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                listcustomer.add(new Tipo_medio(
                    rs.getInt("MediaTypeId"),
                    rs.getString("Name")));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ ex);
        }
        return listcustomer;
    }
  public ObservableList<listas> getlistas() {
        ObservableList<listas> playlist= FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM playlist";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs =  st.executeQuery(query);
            while(rs.next()){
                playlist.add(new listas(
                    rs.getInt("PlaylistId"),
                    rs.getString("Name")));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ ex);
        }
        return playlist;
    }
    
    public Boolean insertaPista(Pista nuevaPista){
        try{
        String query="insert into track (Name, AlbumId, MediaTypeId, GenreId, Composer, Milliseconds, Bytes, UnitPrice) values(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        
        st.setString(1, nuevaPista.getName());
        st.setInt(2, nuevaPista.getAlbumId());
        st.setInt(3, nuevaPista.getMediaTypeId());
        st.setInt(4, nuevaPista.getGenreld());
        st.setString(5, nuevaPista.getComposer());
        st.setInt(6, nuevaPista.getMilliseconds());
        st.setInt(7, nuevaPista.getBytes());
        st.setDouble(8, nuevaPista.getUnitPrice());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
        e.printStackTrace();
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
     public Boolean insertaTipoMedio(Tipo_medio nuevoTipoMedio){
        try{
        String query="insert into mediatype (Name) values(?)";
        PreparedStatement st=Conexion.prepareStatement(query);
        st.setString(1, nuevoTipoMedio.getName());
        st.execute();
        return true;
    }catch(SQLException e)
    {
        System.out.println(e);
    }
    return false;//Nos indica se se hizo bien la inserción en la base de datos
    }
    
    
    public boolean ActualizaPistas(Integer _TrackId, String _Name, Integer _AlbumId, Integer _MediaTypeId, Integer _Genreld,
            String _Composer, Integer _Milliseconds, Integer _Bytes, Double _UnitPrice){
                   
        try
        {
           String query ="UPDATE track "
                   + "SET  Name = ?,"
                   + " AlbumId= ?,"
                   + "MediaTypeId = ?,"
                   + " GenreId=?,"
                   + " Composer= ?,"
                   + " Milliseconds=?, "
                   + "Bytes = ?,"
                   + "UnitPrice= ? "
                   +"WHERE TrackId = ? ";
            PreparedStatement st =Conexion.prepareStatement(query);
            
            st.setString(1, _Name);
            st.setInt(2, _AlbumId);
            st.setInt(3, _MediaTypeId);
            st.setInt(4, _Genreld);
            st.setString(5, _Composer);
            st.setInt(6, _Milliseconds);
            st.setInt(7, _Bytes);
            st.setDouble(8, _UnitPrice);
            st.setInt(9, _TrackId);
            
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean eliminarPista(Integer TrackId)   {
        try
        {
            String query =" DELETE FROM track WHERE TrackId = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, TrackId);
            st.execute();
          
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
     
     public boolean eliminarPista2(Integer TrackId)   {
        try
        {
            String query =" DELETE FROM invoiceline WHERE TrackId = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, TrackId);
            st.execute();
          
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    
    public boolean eliminarPista3(Integer TrackId)   {
        try
        {
            String query =" DELETE FROM playlisttrack WHERE TrackId = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, TrackId);
            st.execute();
          
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminaTiposMedio(int MediaTypeId){
        try
        {
            String query ="DELETE FROM mediatype WHERE MediaTypeId = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setInt(1, MediaTypeId); 
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean ActualizaTipo_Medio(Integer _MediaTypeId, String _Name){
        try
        {
            String query ="UPDATE mediatype SET Name = ? WHERE MediaTypeId = ?";
            PreparedStatement st =Conexion.prepareStatement(query);
            st.setString(1, _Name);
            st.setInt(2, _MediaTypeId);
            st.execute();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }
    
    
    
    
    
    
    public ObservableList<Empleado> getEmpleadosPorPuesto(String _title) {
        ObservableList<Empleado> listEmployees= FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM employee where Title='"+_title+"'";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                listEmployees.add(new Empleado(
                        rs.getInt("EmployeeId"),
                    rs.getString("LastName"),
                    rs.getString("FirstName"),
                    rs.getString("Title"),
                    rs.getInt("ReportsTo"),
                    rs.getDate("BirthDate"),
                    rs.getDate("HireDate"),
                    rs.getString("Address"),
                    rs.getString("City"),
                    rs.getString("State"),
                    rs.getString("Country"),
                    rs.getString("PostalCode"),
                    rs.getString("Phone"),
                    rs.getString("Fax"),
                    rs.getString("Email")));
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return listEmployees;
    }
    
    public ResultSet getClientesDeEmpleadoResultSet(Integer _SupportRepId){
        String query="SELECT * FROM customer WHERE SupportRepId = '"+_SupportRepId+"'";
        ResultSet rs=null;
        try{
            Statement st= Conexion.createStatement();
            rs=st.executeQuery(query);
        }catch(Exception e){
            System.out.println("error al ejecutar"+e);
        }
        return rs;
    }
    
    public ObservableList<PistaxLectura> getPistasLectura() {
        ObservableList<PistaxLectura> listtrack= FXCollections.observableArrayList();
        
        try {
            String query = "SELECT * FROM track";
            Statement st = Conexion.createStatement();
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                listtrack.add(new PistaxLectura(
                    rs.getInt("TrackId"),
                    rs.getString("Name"),
                    rs.getInt("AlbumId"),
                    rs.getInt("MediaTypeId"),
                    rs.getInt("GenreId"),
                    rs.getString("Composer"),
                    rs.getInt("Milliseconds"),
                    rs.getInt("Bytes"),
                    rs.getDouble("UnitPrice")
                ));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar información..."+ex);
        }
        return listtrack;
    }
    */
}