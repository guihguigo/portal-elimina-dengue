package utils;

import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by Alexandre on 26/05/2015.
 */
public final class ScreenSizeHelper {
    private int fonteTitulo, fonteCorpo;
    private int scaleX, scaleY;

    public ScreenSizeHelper(Context mContext) {
        atualizaFonteTamanho(mContext);
    }

    public int getFonteTitulo() {
        return fonteTitulo;
    }

    public int getFonteCorpo() {
        return fonteCorpo;
    }

    public int getTamanhoTela(Context mContext) {
        if ((mContext.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_SMALL) {
            return 0;
        } else if ((mContext.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            return 1;
        } else if ((mContext.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_LARGE) {
            return 2;
        } else if ((mContext.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            return 3;
        }

        return -1;
    }

    private void atualizaFonteTamanho(Context context){
        switch (getTamanhoTela(context)) {
            case 0:
                fonteTitulo = 12;
                fonteCorpo = 10;
                break;
            case 1:
                fonteTitulo = 18;
                fonteCorpo = 16;
                break;
            case 2:
                fonteTitulo = 18
                ;
                fonteCorpo = 18;
                break;
            case 3:
                fonteTitulo = 28;
                fonteCorpo = 26;
        }
    }
}
