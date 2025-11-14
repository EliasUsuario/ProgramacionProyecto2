package biblioteca.simple.servicios;

import biblioteca.simple.modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
public final class SocioUsuario {

    private static final String ARCHIVO = "usuarios.json";

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private SocioUsuario() {}

    public static void exportar(List<Usuario> usuarios) throws IOException {
        try (Writer w = new OutputStreamWriter(new FileOutputStream(ARCHIVO), StandardCharsets.UTF_8)) {
            gson.toJson(usuarios, w);
        }
    }

    public static List<Usuario> importar() throws IOException {
        File f = new File(ARCHIVO);
        if (!f.exists()) return new ArrayList<>();
        try (Reader r = new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8)) {
            Type tipoLista = new TypeToken<ArrayList<Usuario>>() {}.getType();
            List<Usuario> lista = gson.fromJson(r, tipoLista);
            return (lista != null) ? lista : new ArrayList<>();
        }
    }

}
