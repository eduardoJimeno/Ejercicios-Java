package com.bosonit.formacion.block16springcloud.application;

import com.bosonit.formacion.block16springcloud.controller.dto.ClienteDto;
import com.bosonit.formacion.block16springcloud.controller.dto.ClienteMapper;
import com.bosonit.formacion.block16springcloud.controller.dto.ViajeDto;
import com.bosonit.formacion.block16springcloud.controller.dto.ViajeMapper;
import com.bosonit.formacion.block16springcloud.domain.Cliente;
import com.bosonit.formacion.block16springcloud.domain.Viaje;
import com.bosonit.formacion.block16springcloud.pool.MyDataSource;
import lombok.Getter;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ViajeServicioImpl implements ViajeServicio{

    @Autowired
    ClienteServicio clienteServicio;
    ViajeMapper mapperViaje = Mappers.getMapper(ViajeMapper.class);
    ClienteMapper mapperCliente = Mappers.getMapper(ClienteMapper.class);
    //Patron Singleton
    @Getter
    private static ViajeServicioImpl instance;

    static{
        instance = new ViajeServicioImpl();
    }
    private ViajeServicioImpl(){ }
    @Override
    public ViajeDto addViaje(Viaje viaje) throws SQLException {
        String sql = """
                INSERT INTO viaje (origen, destino, fecha_salida, fecha_llegada, status)
                VALUES (?,?,?,?,?)
                """;
        int result;

        try(Connection conn = MyDataSource.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, viaje.getOrigen());
            pstm.setString(2, viaje.getDestino());
            pstm.setDate(3, (Date) viaje.getFechaSalida());
            pstm.setDate(4, (Date) viaje.getFechaLlegada());
            pstm.setBoolean(5, viaje.isStatus());

            result = pstm.executeUpdate();
            if (result > 0) {
                // Recupera las claves generadas automáticamente (incluido el idCliente)
                try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        viaje.setIdViaje(generatedId);
                        return mapperViaje.viajeToViajeDto(viaje);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public ViajeDto getViajeById(int idViaje) throws SQLException {
        return recuperarViaje(idViaje);
    }

    @Override
    public List<ViajeDto> getAllViajes(int pageNumber, int pageSize) throws SQLException {
        if (pageNumber <=0){
            pageNumber =1;
        }
        if (pageSize <=0){
            pageSize =4;
        }
        String sql = "SELECT * FROM viaje LIMIT ? OFFSET ?";
        int offset = (pageNumber - 1) * pageSize;

        List<ViajeDto> result = new ArrayList<>();

        try(Connection conn = MyDataSource.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setInt(1, pageSize);
            pstm.setInt(2, offset);

            try (ResultSet rs = pstm.executeQuery()){
                Viaje viaje;
                while (rs.next()){
                    viaje = new Viaje();
                    viaje.setIdViaje(rs.getInt("id_viaje"));
                    viaje.setOrigen(rs.getString("origen"));
                    viaje.setDestino(rs.getString("destino"));
                    viaje.setFechaSalida(rs.getDate("fecha_salida"));
                    viaje.setFechaLlegada(rs.getDate("fecha_llegada"));
                    viaje.setStatus(rs.getBoolean("status"));

                    result.add(mapperViaje.viajeToViajeDto(viaje));
                }
            }
        }
        return result;
    }

    @Override
    public ViajeDto updateViaje(ViajeDto viajeDto) throws SQLException {

        String sql = """
                UPDATE viaje SET
                    origen = ?, destino = ?,
                    fecha_salida = ?, fecha_llegada = ?, status = ?
                WHERE id_viaje = ?
                """;
        int result;

        try(Connection conn = MyDataSource.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)){

            pstm.setString(1, viajeDto.getOrigen());
            pstm.setString(2, viajeDto.getDestino());
            pstm.setDate(3, (Date) viajeDto.getFechaSalida());
            pstm.setDate(4, (Date) viajeDto.getFechaLlegada());
            pstm.setBoolean(5, viajeDto.isStatus());
            pstm.setInt(6, viajeDto.getIdViaje());

            result = pstm.executeUpdate();
        }

        return viajeDto;
    }

    @Override
    public void deleteViaje(int idViaje) throws SQLException {
        String sql = "DELETE FROM viaje WHERE id_viaje = ?";

        try(Connection conn = MyDataSource.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setInt(1, idViaje);
            pstm.executeUpdate();
        }
    }

    @Override
    public ViajeDto addClienteToViaje(int idViaje, int idCliente) throws SQLException {
        ViajeDto viajeDto = getViajeById(idViaje);

        if(viajeDto != null){
            ClienteDto clienteDto = clienteServicio.getClienteById(idCliente);

            if(clienteDto != null){
                viajeDto.getPasajeros().add(mapperCliente.clienteDtoToCliente(clienteDto));
                updateViaje(viajeDto);
                return viajeDto;
            }
        }
        return null;
    }

    @Override
    public void updateViajeStatus(int idViaje, Boolean viajeStatus) throws SQLException {
        ViajeDto viajeDto = getViajeById(idViaje);

        if(viajeDto != null){
            viajeDto.setStatus(viajeStatus);
            updateViaje(viajeDto);
        }
    }

    @Override
    public Boolean getDisponibilidad(int idViaje) throws SQLException {
        Viaje result = null;
        String sql = "SELECT status FROM viaje WHERE id_viaje = ?";
        try(Connection conn = MyDataSource.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setInt(1, idViaje);
             try (ResultSet rs = pstm.executeQuery()){
                 if(rs.next()){
                     return rs.getBoolean("status");
                 }
             }
        }
        return null;
    }

    public ViajeDto recuperarViaje(int idViaje) throws SQLException {

        Viaje result = null;

        String sql = "SELECT * FROM viaje WHERE id_viaje = ?";

        try (Connection conn = MyDataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, idViaje);
            try(ResultSet rs = pstm.executeQuery()) {
                while (rs.next()){
                    result = new Viaje();
                    result.setIdViaje(rs.getInt("id_viaje"));
                    result.setOrigen(rs.getString("origen"));
                    result.setDestino(rs.getString("destino"));
                    result.setFechaSalida(rs.getDate("fecha_salida"));
                    result.setFechaLlegada(rs.getDate("fecha_llegada"));
                    result.setStatus(rs.getBoolean("status"));

                }
            }
            return mapperViaje.viajeToViajeDto(result);
        }
    }
}
