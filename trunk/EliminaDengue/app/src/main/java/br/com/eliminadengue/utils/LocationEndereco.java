package br.com.eliminadengue.utils;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;

public class LocationEndereco extends Service implements LocationListener {

	private final Context mContext;

	boolean isGPSLigado = false;
	boolean isInternetLigada = false;
	public boolean isLocalAtualizado = false;

	Location mLocation;
	double mLatitude;
	double mLongitude;

	private static final long TEMPO = 20000;
	private static final long DISTANCIA = 20;

	protected LocationManager mLocationManager;

	public LocationEndereco(Context context) {
		this.mContext = context;
		mLocationManager = (LocationManager) mContext
				.getSystemService(LOCATION_SERVICE);

	}

    public double getLatitude() {
        if (mLocation != null) {
            mLatitude = mLocation.getLatitude();
        }
        return mLatitude;
    }


    public double getLongitude() {
        if (mLocation != null) {
            mLongitude = mLocation.getLongitude();
        }
        return mLongitude;
    }


	public Location getLocation() {
		try {

			isGPSLigado = mLocationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER);

			if (isGPSLigado) {
				mLocationManager.requestLocationUpdates(
						LocationManager.GPS_PROVIDER, TEMPO, DISTANCIA, this);
				if (mLocationManager != null) {
					mLocation = mLocationManager
							.getLastKnownLocation(LocationManager.GPS_PROVIDER);
					if (mLocation != null) {
						mLatitude = mLocation.getLatitude();
						mLongitude = mLocation.getLongitude();
						isLocalAtualizado = true;
						return mLocation;
					}
				}
			}

			isInternetLigada = mLocationManager
					.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (isInternetLigada) {
				mLocationManager.requestLocationUpdates(
						LocationManager.NETWORK_PROVIDER, TEMPO, DISTANCIA, this);
				if (mLocationManager != null) {
					mLocation = mLocationManager
							.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
					if (mLocation != null) {
						mLatitude = mLocation.getLatitude();
						mLongitude = mLocation.getLongitude();
						isLocalAtualizado = true;
						return mLocation;
					}
				}
			}

			if (!isGPSLigado) {
				dialogHabilitarGPS();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		isLocalAtualizado = false;
		return null;
	}

	public void dialogHabilitarGPS() {
		AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(mContext);

		mAlertDialog.setTitle("GPS Desabilitado")
		.setMessage("Deseja habilitar a função GPS para que seus dados sejam atualizados?")
		.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				mContext.startActivity(intent);
				}
			})
			.setNegativeButton("Não",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
					}
				}).show();
	}


	@Override
	public void onLocationChanged(Location location) {
		mLatitude = location.getLatitude();
		mLongitude = location.getLongitude();
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

}
