package app_utils.ktteam.src.Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;

public class FilesUtil {

    public static final String ID = "info_id.ser";

    public static final String HOTEN = "info_ho_ten.ser";

    public static final String DIACHI = "info_dc.ser";

    public  static final String GIOITINH = "info_gt.ser";

    public static final String DOB = "info_dob.ser";

    public static final  String NUMBERPHONE = "info_number.ser";

    public static  final String EMAIL = "info_email.ser";

    public static final String AVATAR = "info_avater.ser";

    public static final String TRANGTHAIHOATDONG = "info_actived.ser";

    public static void saveInformation(String data, FileOutputStream fos)
    {
        try
        {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
            objectOutputStream.writeObject(data);
            objectOutputStream.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readInformation(FileInputStream fis)
    {
        try
        {
            ObjectInputStream objectInputStream = new ObjectInputStream(fis);
            String data = (String) objectInputStream.readObject();
            objectInputStream.close();
            fis.close();
            return data;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
