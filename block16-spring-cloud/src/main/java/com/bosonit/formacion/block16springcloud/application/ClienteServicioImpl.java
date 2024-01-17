package com.bosonit.formacion.block16springcloud.application;

import com.bosonit.formacion.block16springcloud.controller.dto.ClienteDto;
import com.bosonit.formacion.block16springcloud.controller.dto.ClienteMapper;
import com.bosonit.formacion.block16springcloud.domain.Cliente;
import com.bosonit.formacion.block16springcloud.pool.MyDataSource;
import lombok.Getter;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServicioImpl implements ClienteServicio{

    ClienteMapper mapper = Mappers.getMapper(ClienteMapper.class);
    //Patron Singleton
    @Getter
    private static ClienteServicioImpl instance;

    static{
        instance = new ClienteServicioImpl();
    }
    private ClienteServicioImpl(){ }


    @Override
    public ClienteDto addCliente(Cliente cliente) throws SQLException {
        String sql = """
                INSERT INTO cliente (nombre, apellido, edad, email, telefono)
                VALUES (?,?,?,?,?)
                """;
        int result;

        try(Connection conn = MyDataSource.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, cliente.getNombre());
            pstm.setString(2, cliente.getApellido());
            pstm.setInt(3, cliente.getEdad());
            pstm.setString(4, cliente.getEmail());
            pstm.setInt(5, cliente.getTelefono());

            result = pstm.executeUpdate();
            if (result > 0) {
                // Recupera las claves generadas automáticamente (incluido el idCliente)
                try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        cliente.setIdCliente(generatedId);
                        return mapper.clienteToClienteDto(cliente);
                    }
                }
            }
        }
        return null;
    }
    @Override
    public ClienteDto getClienteById(int idCliente) throws SQLException {
        return recuperarCliente(idCliente);
    }

    @Override
    public List<ClienteDto> getAllClientes(int pageNumber, int pageSize) throws SQLException {
        if (pageNumber <=0){
            pageNumber =1;
        }
        if (pageSize <=0){
            pageSize =4;
        }
        String sql = "SELECT * FROM cliente LIMIT ? OFFSET ?";
        int offset = (pageNumber - 1) * pageSize;

        List<ClienteDto> result = new ArrayList<>();

        try(Connection conn = MyDataSource.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setInt(1, pageSize);
            pstm.setInt(2, offset);

           try (ResultSet rs = pstm.executeQuery()){
               Cliente cliente;
               while (rs.next()){
                   cliente = new Cliente();
                   cliente.setIdCliente(rs.getInt("id_cliente"));
                   cliente.setNombre(rs.getString("nombre"));
                   cliente.setApellido(rs.getString("apellido"));
                   cliente.setEdad(rs.getInt("edad"));
                   cliente.setEmail(rs.getString("email"));
                   cliente.setTelefono(rs.getInt("telefono"));

                   result.add(mapper.clienteToClienteDto(cliente));
               }
           }
        }
        return result;
    }

    @Override
    public ClienteDto updateCliente(ClienteDto clienteDto) throws SQLException {

        String sql = """
                UPDATE cliente SET
                    nombre = ?, apellido = ?,
                    edad = ?, email = ?, telefono = ?
                WHERE id_cliente = ?
                """;
        int result;

        try(Connection conn = MyDataSource.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)){

            pstm.setString(1, clienteDto.getNombre());
            pstm.setString(2, clienteDto.getApellido());
            pstm.setInt(3, clienteDto.getEdad());
            pstm.setString(4, clienteDto.getEmail());
            pstm.setInt(5, clienteDto.getTelefono());
            pstm.setInt(6, clienteDto.getIdCliente());

            result = pstm.executeUpdate();
        }

        return clienteDto;
    }

    @Override
    public void deleteCliente(int idCliente) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";

        try(Connection conn = MyDataSource.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setInt(1, idCliente);
            pstm.executeUpdate();
        }

    }

    @Override
    public int getClientesByViaje(int idViaje) throws SQLException {
        String sql = "SELECT COUNT(*) FROM cliente WHERE id_viaje = ?";
        int count = 0;

        try (Connection conn = MyDataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, idViaje);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        }
        return count;
    }

    public ClienteDto recuperarCliente(int idCliente) throws SQLException {

        Cliente result = null;

        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";

        try (Connection conn = MyDataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, idCliente);
            try(ResultSet rs = pstm.executeQuery()) {
                while (rs.next()){
                    result = new Cliente();
                    result.setIdCliente(rs.getInt("id_cliente"));
                    result.setNombre(rs.getString("nombre"));
                    result.setApellido(rs.getString("apellido"));
                    result.setEdad(rs.getInt("edad"));
                    result.setEmail(rs.getString("email"));
                    result.setTelefono(rs.getInt("telefono"));

                }
            }
            return mapper.clienteToClienteDto(result);
        }
    }
}
