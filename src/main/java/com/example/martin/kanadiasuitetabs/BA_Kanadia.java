package com.example.martin.kanadiasuitetabs;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;



import java.util.Set;

/**
 * Created by Martin on 20.02.2017.
 */

public class BA_Kanadia {

    private BluetoothAdapter BA_K;

    public String[] can_id = new String[]{"0000012F","00000130","00000138","0000013C","00000140","00000141","0000040C","0000040D","0000040F"};
    static String[] can_data_id = new String[10];
    private BluetoothSocket baSocket;
    private OutputStream baOutStream;
    private InputStream bainstream;
    BluetoothDevice CAN_BA_Device;
    Thread workThr;
    private byte[] readbuf;
    private int readbufpos;
    private int counter;
    private volatile boolean stopjob;

    public  void post_ba()
    {
        BA_K = BluetoothAdapter.getDefaultAdapter();
    }


    public  int start_ba() throws IOException
    {
        int ret_en=0;
        if (!BA_K.isEnabled())
        {
            BA_K.enable();

            ret_en=1;

        } else {

            ret_en=0;
        }
        return ret_en;
    }



    public void paired_dev() throws IOException
    {
        Set<BluetoothDevice> pairedDevices = BA_K.getBondedDevices();

        if(pairedDevices.size() > 0)
        {
            for(BluetoothDevice device : pairedDevices)
            {
                if(device.getName().equals("CAN2BT www.case.eu"))
                {
                    CAN_BA_Device = device;
                    break;
                }
            }
        }


        openBT();
    }

    public void openBT() throws IOException
    {
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); //SerialPortService ID
        baSocket = CAN_BA_Device.createRfcommSocketToServiceRecord(uuid);
        baSocket.connect();
        baOutStream = baSocket.getOutputStream();
        bainstream = baSocket.getInputStream();

        listen_can_data();
    }


    public void listen_can_data()
    {
        final Handler handler = new Handler();
        final byte delimiter = 10; //ASCII code for a newline character

        stopjob = false;
        readbufpos = 0;
        readbuf = new byte[4096];
        workThr = new Thread(new Runnable()
        {
            public void run()
            {
                while(!Thread.currentThread().isInterrupted() && !stopjob)
                {
                    try
                    {
                        int bytesAvailable = bainstream.available();
                        if(bytesAvailable > 0)
                        {
                            long startTime = System.nanoTime();
                            byte[] packetBytes = new byte[bytesAvailable];
                            bainstream.read(packetBytes);
                            long stopTime = System.nanoTime();
                            long elapsedTime = stopTime - startTime;
                            long el_mean = mean_value_be(elapsedTime);
                            System.out.println(el_mean);

                            for(int i=0;i<bytesAvailable;i++)
                            {

                                byte b = packetBytes[i];
                                if(b == delimiter)
                                {

                                    byte[] encodedBytes = new byte[readbufpos];
                                    System.arraycopy(readbuf, 0, encodedBytes, 0, encodedBytes.length);
                                    final String data = new String(encodedBytes, "US-ASCII");



                                    for(int n=0;n < can_id.length;n++)
                                    {
                                        if(data.contains(can_id[n]))
                                        {
                                            can_data_id[n]=data;
                                        }

                                    }


                                    readbufpos = 0;

                                    handler.post(new Runnable()
                                    {
                                        public void run()
                                        {

                                        }
                                    });
                                }
                                else
                                {
                                    readbuf[readbufpos++] = b;
                                }



                            }

                        }
                    }
                    catch (IOException ex)
                    {
                        stopjob = true;
                    }
                }
            }
        });

        workThr.start();
    }

    int mean_val_step=1000;
    static long sum_val;
    static int la=0;
    public long[] mean_val = new long[mean_val_step];
    public long mean_value_be(long val){
        long mean=0;

        mean_val[la]=val;
        la++;
        if(la==mean_val_step){la=0;}
        for (int x=0;x<mean_val_step;x++){sum_val=sum_val + mean_val[x];}
        mean=sum_val/mean_val_step;
        sum_val=0;

        return mean;
    }

    public void stop_ba() throws IOException
    {
        BA_K.disable();
        stopjob = true;
        baOutStream.close();
        bainstream.close();
        baSocket.close();
    }

}
/**
 * Created by Martin on 16.06.2017.
 */


