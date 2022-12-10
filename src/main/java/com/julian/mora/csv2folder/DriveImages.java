/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.julian.mora.csv2folder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author macbook
 */
public class DriveImages {

    private static void DownloadResource(String urlStr, String fileName) {
        try {
            URL url = new URL(urlStr);
            URLConnection connection = url.openConnection();
            String mimeType = connection.getContentType();
            String fileWithExtension = fileName.trim().concat(".").concat(mimeType.split("/")[1]);

            try (ReadableByteChannel rbc = Channels.newChannel(url.openStream()); FileOutputStream fos = new FileOutputStream(fileWithExtension)) {
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(DriveImages.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DriveImages.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DriveImages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int DonwloadDriveImage(String driveImageURLs, String destinationFolder) {
        final String urlBase = "https://drive.google.com/uc?export=download&id=";
        final String[] imageUrls = driveImageURLs.split(",");
        int downloadCounter = 0;
        for (String imageUrl : imageUrls) {
            final String imageURL = imageUrl.trim();
            if (!imageURL.isEmpty() && imageURL.startsWith("https")) {
                Pattern pattern = Pattern.compile("id=(.*)&?", Pattern.CASE_INSENSITIVE);
                Matcher match = pattern.matcher(imageUrl);
                if (match.find()) {
                    String imageToDownload = urlBase + match.group(1);
                    System.out.println("Download: " + imageToDownload);

                    final File dir = new File(destinationFolder, match.group(1));

                    DriveImages.DownloadResource(imageToDownload, dir.getAbsolutePath());
                } else {
                    System.out.println("NO MATCH");
                }
            }
        }

        return downloadCounter;
    }
}
