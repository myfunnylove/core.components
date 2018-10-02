package locidnet.core.components.utils;

import android.location.Address;
import android.location.Geocoder;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;



public class FunctionsJava {

    static String TAG = "Functions";
    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

//    public static String getAddress(Double latitude, Double longitude){
//        MLog.INSTANCE.d("get adress ",""+latitude +" "+longitude);
//        Geocoder geocoder;
//        List<Address> addresses;
//        geocoder = new Geocoder(App.Companion.getInstance(), Locale.getDefault());
//
//        try {
//            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//            String city = addresses.get(0).getLocality();
//            String state = addresses.get(0).getAdminArea();
//            String country = addresses.get(0).getCountryName();
//            String postalCode = addresses.get(0).getPostalCode();
//            String knownName = addresses.get(0).getFeatureName();
//            return  address ;
//        } catch (Exception e) {
//            return "";
//        }
//
//
//    }
    public static TextWatcher EditTelephoneWatcher = new TextWatcher() {
        // int len = 0;
        String text = "";
        boolean editingBefore = false;
        boolean editingOnChanged = false;
        boolean editingAfter = false;

        public void afterTextChanged(Editable str) {
            if (!editingAfter && editingBefore && editingOnChanged) {
                editingAfter = true;
                str.replace(0, str.length(), text);
                // str.append(text);
                Log.v(TAG, "afterTextChanged :" + str + " text :" + text);

                editingBefore = false;
                editingOnChanged = false;
                editingAfter = false;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            if (!editingBefore) {
                editingBefore = true;
                // text = clearText(s.toString());
                Log.v(TAG, "beforeTextChanged :" + s + " count :" + count
                        + " after :" + after + " text :" + text);
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            String d = " ";
            if (!editingOnChanged && editingBefore) {
                editingOnChanged = true;
                text = clearText(s.toString());
                if (text.length() == 4) {
                    text = text.substring(0, 3) + d
                            + text.substring(3, text.length());
                } else if (text.length() == 5) {
                    text = text.substring(0, 3) + d
                            + text.substring(3, text.length());
                } else if (text.length() == 6) {
                    text = text.substring(0, 3) + d
                            + text.substring(3, text.length() - 1) + d
                            + text.substring(text.length() - 1, text.length());
                } else if (text.length() == 7) {
                    text = text.substring(0, 3) + d
                            + text.substring(3, text.length() - 2) + d
                            + text.substring(text.length() - 2, text.length());
                } else if (text.length() > 7) {
                    text = text.substring(text.length() - 7, text.length() - 4)
                            + d
                            + text.substring(text.length() - 4,
                            text.length() - 2) + d
                            + text.substring(text.length() - 2, text.length());
                }

                Log.v(TAG, "onTextChanged :" + s + " count :" + count
                        + " before :" + before + " text :" + text);
            }

        }
    };
    public static TextWatcher EditTelephoneCodeWatcher = new TextWatcher() {
        // int len = 0;
        String text = "";
        boolean editingBefore = false;
        boolean editingOnChanged = false;
        boolean editingAfter = false;

        public void afterTextChanged(Editable str) {
            if (!editingAfter && editingBefore && editingOnChanged) {
                editingAfter = true;
                str.replace(0, str.length(), text);
                // str.append(text);
                Log.v(TAG, "afterTextChanged :" + str + " text :" + text);

                editingBefore = false;
                editingOnChanged = false;
                editingAfter = false;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            if (!editingBefore) {
                editingBefore = true;
                // text = clearText(s.toString());
                Log.v(TAG, "beforeTextChanged :" + s + " count :" + count
                        + " after :" + after + " text :" + text);
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            String d = " ";
            if (!editingOnChanged && editingBefore) {
                editingOnChanged = true;
                text = clearText(s.toString());
                if (text.length() == 3 || text.length() == 4
                        || text.length() == 5) {
                    text = text.substring(0, 2) + d
                            + text.substring(2, text.length());
                } else if (text.length() == 6) {
                    text = text.substring(0, 2) + d
                            + text.substring(2, text.length() - 1) + d
                            + text.substring(text.length() - 1, text.length());
                } else if (text.length() == 7) {
                    text = text.substring(0, 2) + d
                            + text.substring(2, text.length() - 2) + d
                            + text.substring(text.length() - 2, text.length());
                } else if (text.length() == 8 || text.length() == 9) {
                    text = text.substring(0, 2) + d + text.substring(2, 5) + d
                            + text.substring(5, 7) + d
                            + text.substring(7, text.length());
                }

                Log.v(TAG, "onTextChanged :" + s + " count :" + count
                        + " before :" + before + " text :" + text);
            }

        }
    };
    public static TextWatcher EditCardWatcher = new TextWatcher() {
        // int len = 0;
        String text = "";
        boolean editingBefore = false;
        boolean editingOnChanged = false;
        boolean editingAfter = false;

        public void afterTextChanged(Editable str) {
            if (!editingAfter && editingBefore && editingOnChanged) {
                editingAfter = true;
                str.replace(0, str.length(), text);
                // str.append(text);
                Log.v(TAG, "afterTextChanged :" + str + " text :" + text);

                editingBefore = false;
                editingOnChanged = false;
                editingAfter = false;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            if (!editingBefore) {
                editingBefore = true;
                // text = clearText(s.toString());
                Log.v(TAG, "beforeTextChanged :" + s + " count :" + count
                        + " after :" + after + " text :" + text);
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            String d = " ";
            if (!editingOnChanged && editingBefore) {
                editingOnChanged = true;
                text = clearText(s.toString());
                if ((text.length() > 4) && (text.length() <= 8)) {
                    text = text.substring(0, 4) + d
                            + text.substring(4, text.length());
                } else if ((text.length() > 8) && (text.length() <= 12)) {
                    text = text.substring(0, 4) + d + text.substring(4, 8) + d
                            + text.substring(8, text.length());
                } else if ((text.length() > 12) && (text.length() <= 16)) {
                    text = text.substring(0, 4) + d + text.substring(4, 8) + d
                            + text.substring(8, 12) + d
                            + text.substring(12, text.length());
                }
                Log.v(TAG, "onTextChanged :" + s + " count :" + count
                        + " before :" + before + " text :" + text);
            }
        }
    };

    public static NumberKeyListener EditCardKey = new NumberKeyListener() {

        @Override
        public int getInputType() {
            return InputType.TYPE_CLASS_NUMBER;
        }

        @Override
        protected char[] getAcceptedChars() {
            return new char[]{' ', '1', '2', '3', '4', '5', '6', '7', '8',
                    '9', '0'};
        }
    };

    public static NumberKeyListener EditTelephoneKey = new NumberKeyListener() {

        @Override
        public int getInputType() {
            return InputType.TYPE_CLASS_NUMBER;
        }

        @Override
        protected char[] getAcceptedChars() {
            return new char[]{' ', '1', '2', '3', '4', '5', '6', '7', '8',
                    '9', '0'};
        }
    };

    public static TextWatcher EditSummaWatcher = new TextWatcher() {
        String text = "";
        boolean editingBefore = false;
        boolean editingOnChanged = false;
        boolean editingAfter = false;

        public void afterTextChanged(Editable str) {
            if (!editingAfter && editingBefore && editingOnChanged) {
                editingAfter = true;
                if (!text.startsWith("0")) {
                    str.replace(0, str.length(), text);
                } else {
                    str.clear();
                }
                // str.append(text);
                Log.v(TAG, "afterTextChanged :" + str + " text :" + text);

                editingBefore = false;
                editingOnChanged = false;
                editingAfter = false;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
            if (!editingBefore) {
                editingBefore = true;
                text = clearText(s.toString());
                Log.v(TAG, "beforeTextChanged :" + s + " count :" + count
                        + " after :" + after + " text :" + text);
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            String d = " ";
            if (!editingOnChanged && editingBefore) {
                editingOnChanged = true;
                text = clearText(s.toString());

                if (text.length() >= 3) {


                    System.out.println("onTextChanged text length=" + text.length());
                    switch (text.length() % 3) {
                        case 0:
                            System.out.println("onTextChanged text  qoldiq=0");
                            text = getSummaSplitWithThreeNumber(text);
                            System.out.println("onTextChanged text  text=" + text);
                            break;

                        case 1:
                            System.out.println("onTextChanged text  qoldiq=1");
                            text = text.substring(0, 1) + " " + getSummaSplitWithThreeNumber(text.substring(1));
                            System.out.println("onTextChanged text  text=" + text);
                            break;

                        case 2:
                            System.out.println("onTextChanged text  qoldiq=2");
                            text = text.substring(0, 2) + " " + getSummaSplitWithThreeNumber(text.substring(2));
                            System.out.println("onTextChanged text  text=" + text);
                            break;
                    }
                }



               /* if (text.length() == 4) {
                    text = text.substring(0, 1) + d
                            + text.substring(1, text.length());
                } else if (text.length() == 5) {
                    text = text.substring(0, 2) + d
                            + text.substring(2, text.length());
                } else if (text.length() == 6) {
                    text = text.substring(0, 3) + d
                            + text.substring(3, text.length());
                } else if (text.length() == 7) {
                    text = text.substring(0, 1) + d
                            + text.substring(1, text.length() - 3) + " "
                            + text.substring(text.length() - 3, text.length());
                } else if (text.length() > 7) {
                    text = text.substring(0, 1) + d + text.substring(1, 4)
                            + " " + text.substring(4, 7);
                }*/
                Log.v(TAG, "onTextChanged :" + s + " count :" + count
                        + " before :" + before + " text :" + text);
            }
        }
    };

    // 3 ta 3ta qilib summani ajratish funksiyasi
    public static String getSummaSplitWithThreeNumber(String text) {
        String n = "";
        for (int i = 0; i < text.length() / 3; i++) {
            if (i == 0) {
                n += text.substring(i * 3, (i + 1) * 3);
            } else {
                n += " " + text.substring(i * 3, (i + 1) * 3);
            }
        }
        return n;
    }

    // 3 ta 3ta qilib summani ajratish funksiyasi
    public static String getSummaSplitWithThreeNumber(String text, String d) {
        String n = "";
        for (int i = 0; i < text.length() / 3; i++) {
            if (i == 0) {
                n += text.substring(i * 3, (i + 1) * 3);
            } else {
                n += d + text.substring(i * 3, (i + 1) * 3);
            }
        }
        return n;
    }

    public static TextWatcher EditAccountNumberWatcher = new TextWatcher() {
        String text = "";
        boolean editingBefore = false;
        boolean editingOnChanged = false;
        boolean editingAfter = false;

        public void afterTextChanged(Editable str) {
            if (!editingAfter && editingBefore && editingOnChanged) {
                editingAfter = true;
                str.replace(0, str.length(), text);
                // str.append(text);
                Log.v(TAG, "afterTextChanged :" + str + " text :" + text);

                editingBefore = false;
                editingOnChanged = false;
                editingAfter = false;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
            if (!editingBefore) {
                editingBefore = true;
                text = s.toString();
                Log.v(TAG, "beforeTextChanged :" + s + " count :" + count
                        + " after :" + after + " text :" + text);
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            String d = " ";
            if (!editingOnChanged && editingBefore) {
                editingOnChanged = true;
                text = clearText(s.toString());
                if (text.length() > 4 && text.length() <= 8) {
                    text = text.substring(0, 4) + d
                            + text.substring(4, text.length());
                } else if (text.length() > 8 && text.length() <= 12) {
                    text = text.substring(0, 4) + d + text.substring(4, 8) + d
                            + text.substring(8, text.length());
                } else if (text.length() > 12 && text.length() <= 16) {
                    text = text.substring(0, 4) + d + text.substring(4, 8) + d + text.substring(8, 12) + d
                            + text.substring(12, text.length());
                } else if (text.length() > 16 && text.length() <= 20) {
                    text = text.substring(0, 4) + d + text.substring(4, 8) + d + text.substring(8, 12) + d + text.substring(12, 16) + d
                            + text.substring(16, text.length());
                }
                Log.v(TAG, "onTextChanged :" + s + " count :" + count
                        + " before :" + before + " text :" + text);
            }
        }
    };
    public static TextWatcher EditInnNumberWatcher = new TextWatcher() {
        String text = "";
        boolean editingBefore = false;
        boolean editingOnChanged = false;
        boolean editingAfter = false;

        public void afterTextChanged(Editable str) {
            if (!editingAfter && editingBefore && editingOnChanged) {
                editingAfter = true;
                str.replace(0, str.length(), text);
                // str.append(text);
                Log.v(TAG, "afterTextChanged :" + str + " text :" + text);

                editingBefore = false;
                editingOnChanged = false;
                editingAfter = false;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
            if (!editingBefore) {
                editingBefore = true;
                text = s.toString();
                Log.v(TAG, "beforeTextChanged :" + s + " count :" + count
                        + " after :" + after + " text :" + text);
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            String d = " ";
            if (!editingOnChanged && editingBefore) {
                editingOnChanged = true;
                text = clearText(s.toString());
                if (text.length() > 3 && text.length() <= 6) {
                    text = text.substring(0, 3) + d
                            + text.substring(3, text.length());
                } else if (text.length() > 6 && text.length() <= 9) {
                    text = text.substring(0, 3) + d + text.substring(3, 6) + d
                            + text.substring(6, text.length());
                }
                Log.v(TAG, "onTextChanged :" + s + " count :" + count
                        + " before :" + before + " text :" + text);
            }
        }
    };
    public static NumberKeyListener EditSummaKey = new NumberKeyListener() {

        @Override
        public int getInputType() {
            return InputType.TYPE_CLASS_NUMBER;
        }

        @Override
        protected char[] getAcceptedChars() {
            return new char[]{' ', '1', '2', '3', '4', '5', '6', '7', '8',
                    '9', '0'};
        }
    };
    public static NumberKeyListener EditKursKey = new NumberKeyListener() {

        @Override
        public int getInputType() {
            return InputType.TYPE_CLASS_NUMBER;
        }

        @Override
        protected char[] getAcceptedChars() {
            return new char[]{' ', '1', '2', '3', '4', '5', '6', '7', '8',
                    '9', '0', '.'};
        }
    };

    public static NumberKeyListener EditNumberKey = new NumberKeyListener() {

        @Override
        public int getInputType() {
            return InputType.TYPE_CLASS_NUMBER;
        }

        @Override
        protected char[] getAcceptedChars() {
            return new char[]{'1', '2', '3', '4', '5', '6', '7', '8',
                    '9', '0'};
        }
    };

    public static String clearText(String s) {
        s = s.replaceAll("-", "");
        s = s.replaceAll(" ", "");
        s = s.trim();
        return s;
    }

    public static String clearEdit(EditText edit) {
        String s = edit.getText().toString();
        s = s.replaceAll("-", "");
        s = s.replaceAll(" ", "");
        s = s.trim();
        return s;
    }

    public static String formatTel(String s) {
        if (s.length() == 7) {
            s = s.substring(0, 3) + " " + s.substring(3, 5) + " "
                    + s.substring(5, 7);
        } else {
            // Log.v(TAG, "s : = " + s + " 7 " + s.substring(0, s.length() -
            // 7));
            s = s.substring(0, s.length() - 7) + " "
                    + s.substring(s.length() - 7, s.length() - 4) + " "
                    + s.substring(s.length() - 4, s.length() - 2) + " "
                    + s.substring(s.length() - 2, s.length());
            Log.v(TAG, "s : = " + s);
        }

        return s;
    }




    public static boolean isEmailValid(CharSequence email) {
        Pattern EMAIL_PATTERN = Pattern
                .compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        return EMAIL_PATTERN.matcher(email).matches();
    }
    public static void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

}
