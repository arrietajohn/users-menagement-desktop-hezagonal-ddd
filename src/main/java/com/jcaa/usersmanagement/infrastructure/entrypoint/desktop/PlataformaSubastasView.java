package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.SubastaController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.OfertaController;
import com.jcaa.usersmanagement.application.service.dto.command.CreateSubastaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.CreateOfertaCommand;
import com.jcaa.usersmanagement.domain.model.Subasta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PlataformaSubastasView extends JFrame {
    private final SubastaController subastaController;
    private final OfertaController ofertaController;

    private JTable tableSubastas;
    private DefaultTableModel modelSubastas;
    private JTextField txtIdArticulo, txtPrecioInicial, txtDias;
    private JTextField txtOfertante, txtMonto;
    private JLabel lblSeleccion;
    private Integer subastaSeleccionadaId = null;

    public PlataformaSubastasView(SubastaController sc, OfertaController oc) {
        this.subastaController = sc;
        this.ofertaController = oc;

        setTitle("Sistema de Subastas y Ofertas - Arquitectura Limpia");
        setSize(950, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        initComponentes();
        cargarTabla();
    }

    private void initComponentes() {
        JPanel panelArriba = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelArriba.setBorder(BorderFactory.createTitledBorder("Módulo Subastas (Unidad 2)"));
        txtIdArticulo = new JTextField(5);
        txtPrecioInicial = new JTextField(8);
        txtDias = new JTextField(3);
        JButton btnCrear = new JButton("Publicar");
        panelArriba.add(new JLabel("ID Artículo:")); panelArriba.add(txtIdArticulo);
        panelArriba.add(new JLabel("Precio ($):")); panelArriba.add(txtPrecioInicial);
        panelArriba.add(new JLabel("Días:")); panelArriba.add(txtDias);
        panelArriba.add(btnCrear);

        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBorder(BorderFactory.createTitledBorder("Subastas Activas"));
        String[] col = {"ID Subasta", "ID Artículo", "Inicial", "Actual Max", "Fecha Límite", "Estado"};
        modelSubastas = new DefaultTableModel(col, 0) { @Override public boolean isCellEditable(int r, int c) { return false; } };
        tableSubastas = new JTable(modelSubastas);
        panelCentro.add(new JScrollPane(tableSubastas), BorderLayout.CENTER);

        JPanel panelAbajo = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        panelAbajo.setBorder(BorderFactory.createTitledBorder("Módulo Pujas (Unidad 3)"));
        lblSeleccion = new JLabel("Marque una fila arriba ->");
        lblSeleccion.setForeground(Color.BLUE);
        txtOfertante = new JTextField(5);
        txtMonto = new JTextField(8);
        JButton btnPujar = new JButton("Lanzar Oferta");
        panelAbajo.add(lblSeleccion);
        panelAbajo.add(new JLabel("ID Ofertante:")); panelAbajo.add(txtOfertante);
        panelAbajo.add(new JLabel("Monto ($):")); panelAbajo.add(txtMonto);
        panelAbajo.add(btnPujar);

        add(panelArriba, BorderLayout.NORTH); add(panelCentro, BorderLayout.CENTER); add(panelAbajo, BorderLayout.SOUTH);

        tableSubastas.getSelectionModel().addListSelectionListener(e -> {
            int f = tableSubastas.getSelectedRow();
            if (f != -1) {
                subastaSeleccionadaId = (Integer) tableSubastas.getValueAt(f, 0);
                lblSeleccion.setText("Subasta ID Marcada: " + subastaSeleccionadaId + " |");
            }
        });

        btnCrear.addActionListener(e -> {
            try {
                CreateSubastaCommand cmd = new CreateSubastaCommand();
                cmd.setIdArticulo(Integer.parseInt(txtIdArticulo.getText().trim()));
                cmd.setPrecioInicial(new BigDecimal(txtPrecioInicial.getText().trim()));
                cmd.setFechaLimite(LocalDateTime.now().plusDays(Integer.parseInt(txtDias.getText().trim())));
                subastaController.crear(cmd);
                JOptionPane.showMessageDialog(this, "Subasta publicada.");
                cargarTabla();
            } catch(Exception ex) { JOptionPane.showMessageDialog(this, ex.getMessage()); }
        });

        btnPujar.addActionListener(e -> {
            if (subastaSeleccionadaId == null) return;
            try {
                CreateOfertaCommand cmd = new CreateOfertaCommand();
                cmd.setIdSubasta(subastaSeleccionadaId);
                cmd.setIdUsuarioOfertante(Integer.parseInt(txtOfertante.getText().trim()));
                cmd.setMonto(new BigDecimal(txtMonto.getText().trim()));
                ofertaController.pujar(cmd);
                JOptionPane.showMessageDialog(this, "Puja exitosa.");
                cargarTabla();
            } catch(Exception ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Denegado", JOptionPane.ERROR_MESSAGE); }
        });
    }

    private void cargarTabla() {
        modelSubastas.setRowCount(0);
        try {
            List<Subasta> lista = subastaController.listar();
            for (Subasta s : lista) {
                modelSubastas.addRow(new Object[]{ s.getIdSubasta(), s.getIdArticulo(), s.getPrecioInicial(), s.getPrecioActual(), s.getFechaLimite(), s.getEstado() });
            }
        } catch(Exception e) { System.err.println(e.getMessage()); }
    }
}