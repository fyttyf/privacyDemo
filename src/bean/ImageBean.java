package bean;

import java.io.Serializable;

public class ImageBean implements Serializable {
    //public int imageId;
    //public double resolution;

    public void generateImage(int imageId, int resolution){
        String openUrl = "/Users/fengyutian/Desktop/jsc/privacyDemo/web/image/"+imageId+ ".jpg";
        String saveUrl = "/Users/fengyutian/Desktop/jsc/privacyDemo/web/image/tmp";
        String saveName=String.format("%d_%d", imageId, resolution);
        ImageDeal imageDeal = new ImageDeal(openUrl, saveUrl, saveName, "jpg");
        try{
            imageDeal.mosaic((int)Math.floor(264/resolution));
            //Thread.currentThread().sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.printf("Image: %d Resolution: %d was generated.\n", imageId, resolution);
    }
    public static void main(String[] args){
        ImageBean ib = new ImageBean();
        for (int i=6; i<266; i++){
            ib.generateImage(2,i);
        }

    }


}
