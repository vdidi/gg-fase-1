package br.com.zup.vitorhugo.crudspringbootjava;


import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class ArquivoDadoRepository implements ArquivoRepository {

    @Override
    public boolean escrever(Arquivo arquivo) throws IOException{
        try {

            String newKey = arquivo.getKey();
            String newValue = arquivo.getValue();

            String separator;
            String key;
            int index;

            File file = new File("database.txt");

            if (!file.exists()) {

                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;

            while (raf.getFilePointer() < raf.length()) {

                separator = raf.readLine();

                index = separator.indexOf(':');

                // separa key de value.
                key = separator.substring(0, index);

                if (key.equals(newKey)) {
                    found = true;
                    break;
                }

            }

            if (found == false) {

                separator = newKey + ":" + newValue;

                raf.writeBytes(separator);

                raf.writeBytes(System.lineSeparator());

                raf.close();

            }
            // O value é atualizado caso a key já existir
             else if (found){
                File tmpFile = new File("temp.txt");
                RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");

                String argument = "";
                // Seta file pointer para o começo
                raf.seek(0);

                while (raf.getFilePointer() < raf.length()) {

                    argument = raf.readLine();

                    index = argument.indexOf(':');
                    key = argument.substring(0, index);
                    if (key.equals(newKey)) {
                        argument = key + ":" + newValue;
                    }

                    tmpraf.writeBytes(argument);

                    tmpraf.writeBytes(System.lineSeparator());
                }

                // Seta file pointer para o começo
                raf.seek(0);
                tmpraf.seek(0);

                while (tmpraf.getFilePointer() < tmpraf.length()) {
                    raf.writeBytes(tmpraf.readLine());
                    raf.writeBytes(System.lineSeparator());
                }

                raf.setLength(tmpraf.length());

                tmpraf.close();
                raf.close();

                tmpFile.delete();
            }
        }

        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    };

    @Override
    public String recuperar(String key_arg) throws IOException, ClassNotFoundException {
        try {

            String separator;
            String key;
            String value;
            int index;

            File file = new File("database.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            while (raf.getFilePointer() < raf.length()) {

                separator = raf.readLine();

                index = separator.indexOf(':');

                key = separator.substring(0, index);
                value = separator.substring(index + 1);

                if (key.equals(key_arg)) {
                    System.out.println(value);
                    return value;
                }
            }
        }
        catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    };

    @Override
    public boolean remover(String key_arg) {

        try {

            String separator;
            String key;
            int index;

            File file = new File("database.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;

            while (raf.getFilePointer() < raf.length()) {

                separator = raf.readLine();

                index = separator.indexOf(':');

                key = separator.substring(0, index);

                if (key.equals(key_arg)) {
                    found = true;
                    break;
                }
            }

            if (found) {

                File tmpFile = new File("temp.txt");

                RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");

                // Seta file pointer para o começo
                raf.seek(0);

                while (raf.getFilePointer() < raf.length()) {

                    separator = raf.readLine();

                    index = separator.indexOf(':');
                    key = separator.substring(0, index);

                    if (key.equals(key_arg)) {
                        // Pula o insert dos valores
                        // no arquivo temporário
                        continue;
                    }

                    tmpraf.writeBytes(separator);

                    tmpraf.writeBytes(System.lineSeparator());
                }

                raf.seek(0);
                tmpraf.seek(0);

                while (tmpraf.getFilePointer() < tmpraf.length()) {
                    raf.writeBytes(tmpraf.readLine());
                    raf.writeBytes(System.lineSeparator());
                }

                raf.setLength(tmpraf.length());

                tmpraf.close();
                raf.close();

                tmpFile.delete();

                return true;
            } else {

                raf.close();

                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    };

    @Override
    public String listar() throws IOException {
        String return_value = "";

        try {

            String separator;
            String key;
            String value;
            int index;

            File file = new File("database.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            while (raf.getFilePointer() < raf.length()) {

                separator = raf.readLine();

                index = separator.indexOf(':');

                key = separator.substring(0, index);
                value = separator.substring(index + 1);

                return_value = return_value + (key + ":" + value) + "\n";
            }

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
        return return_value;
    }

}
