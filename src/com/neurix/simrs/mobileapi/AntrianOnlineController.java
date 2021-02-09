package com.neurix.simrs.mobileapi;

import com.neurix.common.bo.GeneralBo;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.mobileapi.model.AntrianMobile;
import com.neurix.simrs.mobileapi.model.CheckupMobile;
import com.neurix.simrs.mobileapi.model.HeaderCheckupMobile;
import com.neurix.simrs.mobileapi.model.UserInfo;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.antrianonline.bo.AntrianOnlineBo;
import com.neurix.simrs.transaksi.antrianonline.model.AntianOnline;
import com.neurix.simrs.transaksi.antriantelemedic.bo.TelemedicBo;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.opensymphony.xwork2.ModelDriven;
import io.agora.recording.RecordingEventHandler;
import io.agora.recording.RecordingSDK;
import io.agora.recording.common.Common;
import io.agora.recording.common.RecordingConfig;
import io.agora.recording.common.RecordingEngineProperties;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author gondok
 * Thursday, 21/11/19 12:32
 */

class RecordingCleanTimer extends TimerTask {
    AntrianOnlineController rs;
    public RecordingCleanTimer(AntrianOnlineController rs) {
        this.rs = rs;
    }
    @Override
    public void run() {
        rs.clean();
    }
}

class CreatChannelRunnable implements Runnable {
    RecordingSDK recordingSDK;
    String channelId;
    String uid;
    RecordingConfig config;
    RecordingEventHandler recordingEventHandler;


    public CreatChannelRunnable(RecordingSDK recordingSDK, String channelId, String uid, RecordingConfig config, RecordingEventHandler recordingEventHandler) {
        this.recordingSDK = recordingSDK;
        this.channelId = channelId;
        this.uid = uid;
        this.config = config;
        this.recordingEventHandler = recordingEventHandler;
    }

    @Override
    public void run() {
        recordingSDK.createChannel(CommonConstant.APP_ID, "", channelId, new Integer(uid), config, 5);
        recordingSDK.startService();
    }


    public boolean stop() {
        RecordingEngineProperties recordingEngineProperties = recordingSDK.getProperties();
        boolean isLeave = recordingSDK.leaveChannel();
        recordingSDK.unRegisterOberserver(recordingEventHandler);
        return isLeave;
    }




}

public class AntrianOnlineController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(AntrianOnlineController.class);
    private AntrianMobile model = new AntrianMobile();
    private Collection<AntrianMobile> listOfAntrianOnline = new ArrayList<>();
    private Collection<HeaderCheckupMobile> listOfHeaderCheckup = new ArrayList<>();
    private AntrianOnlineBo antrianOnlineBoProxy;
    private CheckupBo checkupBoProxy;
    private CheckupDetailBo checkupDetailBoProxy;
    private TelemedicBo telemedicBoProxy;

    private String idAntrianOnline;
    private String noCheckupOnline;
    private String idPelayanan;
    private String idDokter;
    private String createdDate;
    private String branchId;
    private String jamAwal;
    private String jamAkhir;
    private String tglCheckup;
    private String action;

    private String idDetailCheckup;
    private String flagCall;
    private String flag;

    private String findNoAntrian;

    private String idPasien;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getFlagCall() {
        return flagCall;
    }

    public void setFlagCall(String flagCall) {
        this.flagCall = flagCall;
    }

    private String channelId;
    private String uid;

    private RecordingEventHandler recordingEventHandler;

    private int keepMediaTime = 0;

    private boolean isMixMode = false;
    private int width = 0;
    private int height = 0;
    private int fps = 0;
    private int kbps = 0;
    private String storageDir = "./";
    private long aCount = 0;
    private long count = 0;
    private long size = 0;
    private Common.CHANNEL_PROFILE_TYPE profile_type;
    Vector<Long> m_peers = new Vector<Long>();
    private RecordingConfig config = null;
    private RecordingSDK RecordingSDKInstance = null;
    private boolean m_receivingAudio = false;
    private boolean m_receivingVideo = false;
    private HashSet<Long> subscribedVideoUids = new HashSet<Long>();
    private HashSet<String> subscribedVideoUserAccount = new HashSet<String>();

    HashMap<String, UserInfo> audioChannels = new HashMap<String, UserInfo>();
    HashMap<String, UserInfo> videoChannels = new HashMap<String, UserInfo>();
    Timer cleanTimer = null;
    private int layoutMode = BESTFIT_LAYOUT;
    private long maxResolutionUid = -1;
    private String maxResolutionUserAccount = "";
    private int keepLastFrame = 0;
    public static final int DEFAULT_LAYOUT = 0;
    public static final int BESTFIT_LAYOUT = 1;
    public static final int VERTICALPRESENTATION_LAYOUT = 2;
    private String userAccount = "";
    private long lastKeepAudioTime = 0;
    private long lastKeepVideoTime = 0;

    private String videoFileName;
    private String audioFileName;

    private Thread recordThread;

    public String getFindNoAntrian() {
        return findNoAntrian;
    }

    public void setFindNoAntrian(String findNoAntrian) {
        this.findNoAntrian = findNoAntrian;
    }

    public void setTelemedicBoProxy(TelemedicBo telemedicBoProxy) {
        this.telemedicBoProxy = telemedicBoProxy;
    }

    public CheckupDetailBo getCheckupDetailBoProxy() {
        return checkupDetailBoProxy;
    }

    public void setCheckupDetailBoProxy(CheckupDetailBo checkupDetailBoProxy) {
        this.checkupDetailBoProxy = checkupDetailBoProxy;
    }

    public CheckupBo getCheckupBoProxy() {
        return checkupBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public Collection<HeaderCheckupMobile> getListOfHeaderCheckup() {
        return listOfHeaderCheckup;
    }

    public void setListOfHeaderCheckup(Collection<HeaderCheckupMobile> listOfHeaderCheckup) {
        this.listOfHeaderCheckup = listOfHeaderCheckup;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public void setModel(AntrianMobile model) {
        this.model = model;
    }

    public Collection<AntrianMobile> getListOfAntrianOnline() {
        return listOfAntrianOnline;
    }

    public void setListOfAntrianOnline(Collection<AntrianMobile> listOfAntrianOnline) {
        this.listOfAntrianOnline = listOfAntrianOnline;
    }

    public String getTglCheckup() {
        return tglCheckup;
    }

    public void setTglCheckup(String tglCheckup) {
        this.tglCheckup = tglCheckup;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getJamAwal() {
        return jamAwal;
    }

    public void setJamAwal(String jamAwal) {
        this.jamAwal = jamAwal;
    }

    public String getJamAkhir() {
        return jamAkhir;
    }

    public void setJamAkhir(String jamAkhir) {
        this.jamAkhir = jamAkhir;
    }

    public String getIdAntrianOnline() {
        return idAntrianOnline;
    }

    public void setIdAntrianOnline(String idAntrianOnline) {
        this.idAntrianOnline = idAntrianOnline;
    }

    public String getNoCheckupOnline() {
        return noCheckupOnline;
    }

    public void setNoCheckupOnline(String noCheckupOnline) {
        this.noCheckupOnline = noCheckupOnline;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public static Logger getLogger() {
        return logger;
    }

    public AntrianOnlineBo getAntrianOnlineBoProxy() {
        return antrianOnlineBoProxy;
    }

    public void setAntrianOnlineBoProxy(AntrianOnlineBo antrianOnlineBoProxy) {
        this.antrianOnlineBoProxy = antrianOnlineBoProxy;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public Object getModel() {
        switch (action){
            case "show":
                return listOfAntrianOnline;
            case "antrianAll":
                return listOfHeaderCheckup;
            case "getByCriteria":
                return listOfAntrianOnline;
            default: return model;
        }

    }

    public HttpHeaders create() {
        logger.info("[AntrianOnlineController.create] start process POST / <<<");

        Timestamp now = new Timestamp(System.currentTimeMillis());

        AntianOnline antianOnline = new AntianOnline();
        antianOnline.setIdPelayanan(idPelayanan);
        antianOnline.setIdDokter(idDokter);
        antianOnline.setNoCheckupOnline(noCheckupOnline);
        antianOnline.setJamAkhir(jamAkhir);
        antianOnline.setJamAwal(jamAwal);
        antianOnline.setTglCheckup(tglCheckup);
        antianOnline.setBranchId(branchId);

        if (action.equalsIgnoreCase("tambah")) {
            try {
                antrianOnlineBoProxy.saveAdd(antianOnline);
            } catch (GeneralBOException e) {
                logger.error("[AntrianOnlineController.tambah] Error save add antrian online " + e.getMessage());
                throw new GeneralBOException("[AntrianOnlineController.tambah] Error save add antrian online");
            }
        }

        if (action.equalsIgnoreCase("show")) {
            List<AntianOnline> result = new ArrayList<>();
            try {
                result = antrianOnlineBoProxy.getAntrianByCriteria(idPelayanan, idDokter, noCheckupOnline, CommonUtil.convertStringToDate(tglCheckup), jamAwal, jamAkhir, branchId, idPasien);
            } catch (GeneralBOException e) {
                logger.error("[AntrianOnlineController.getAntrianAll] Error get antrian all " + e.getMessage());
                throw new GeneralBOException("[AntrianOnlineController.getAntrianAll] Error When Error get antrian all");
            }

            for (AntianOnline item : result) {
                AntrianMobile antrian = new AntrianMobile();
                antrian.setIdAntrianOnline(item.getIdAntrianOnline());
                antrian.setJumlahAntrian(item.getJumlahAntrian());
                antrian.setBranchId(item.getBranchId());
                antrian.setBranchName(item.getBranchName());
                antrian.setIdDokter(item.getIdDokter());
                antrian.setNamaDokter(item.getNamaDokter());
                antrian.setIdPelayanan(item.getIdPelayanan());
                antrian.setNamaPelayanan(item.getNamaPelayanan());
                antrian.setNama(item.getNama());
                antrian.setJamAwal(item.getJamAwal());
                antrian.setJamAkhir(item.getJamAkhir());
                antrian.setNoAntrian(item.getNoAntrian());

                    if  (findNoAntrian != null) {
                    List<AntianOnline> temp = new ArrayList<>();

                    try {
                        temp = antrianOnlineBoProxy.getAntrianByCriteria(idPelayanan, idDokter, null, CommonUtil.convertStringToDate(tglCheckup), jamAwal, jamAkhir, branchId, idPasien);
                    } catch (GeneralBOException e) {
                        logger.error("[AntrianOnlineController.getAntrianAll] Error get antrian all " + e.getMessage());
                        throw new GeneralBOException("[AntrianOnlineController.getAntrianAll] Error When Error get antrian all");
                    }

                    for (AntianOnline item2 : temp) {
                        if (item2.getNoCheckupOnline().equalsIgnoreCase(noCheckupOnline)) {
                            antrian.setNoAntrian(item2.getNoAntrian());
                            break;
                        }
                    }

                } else antrian.setNoCheckupOnline(item.getNoCheckupOnline());
                antrian.setNoCheckup(item.getNoCheckup());
                antrian.setIdDetailCheckup(item.getIdDetailCheckup());
                antrian.setTglCheckup(item.getTglCheckup());
                antrian.setFlagPeriksa(item.getFlagPeriksa());

                listOfAntrianOnline.add(antrian);
            }

        }

        if (action.equalsIgnoreCase("antrianAll")){
            List<HeaderCheckup> result = new ArrayList<>();

            try {
                result = checkupBoProxy.getListAntrian(branchId, idPelayanan);
            } catch (GeneralBOException e) {

            }
            

            if (result.size() > 0){
                int i = 1;
                for (HeaderCheckup item :result){
                    HeaderCheckupMobile headerCheckupMobile = new HeaderCheckupMobile();
                    headerCheckupMobile.setNoAntrian(String.valueOf(i));
                    headerCheckupMobile.setIdPasien(item.getIdPasien());
                    headerCheckupMobile.setNama(item.getNama());
                    headerCheckupMobile.setNamaDesa(item.getNamaDesa());
                    headerCheckupMobile.setNamaPelayanan(item.getNamaPelayanan());
                    headerCheckupMobile.setNamaKecamatan(item.getNamaKecamatan());
                    headerCheckupMobile.setIdDetailCheckup(item.getIdDetailCheckup());
                    headerCheckupMobile.setNoCheckup(item.getNoCheckup());

                    listOfHeaderCheckup.add(headerCheckupMobile);
                    i++;
                }
            }
        }

        if (action.equalsIgnoreCase("getByCriteria")) {

            List<AntianOnline> result = new ArrayList<>();

            AntianOnline bean = new AntianOnline();
            bean.setIdAntrianOnline(idAntrianOnline);
            bean.setNoCheckupOnline(noCheckupOnline);
            bean.setIdPelayanan(idPelayanan);
            bean.setIdDokter(idDokter);
            bean.setIdDetailCheckup(idDetailCheckup);
            bean.setFlag(flag);
            bean.setTglCheckup(tglCheckup);

            try{
               result = antrianOnlineBoProxy.getByCriteria(bean);
            } catch (GeneralBOException e) {
                logger.error("[AntrianOnlineController.getAntrianAll] Error get antrian all " + e.getMessage());
                throw new GeneralBOException("[AntrianOnlineController.getAntrianAll] Error When Error get antrian all");
            }

            if (result.size() > 0) {
                for (AntianOnline item : result) {
                    AntrianMobile antrianMobile = new AntrianMobile();
                    antrianMobile.setIdAntrianOnline(item.getIdAntrianOnline());
                    antianOnline.setNoCheckupOnline(item.getNoCheckupOnline());
                    antianOnline.setIdDokter(item.getIdDokter());
                    antianOnline.setIdDetailCheckup(item.getIdDetailCheckup());
                    antianOnline.setIdPelayanan(item.getIdPelayanan());
                    antianOnline.setBranchId(item.getBranchId());

                    listOfAntrianOnline.add(antrianMobile);
                }
            }
        }

        if  (action.equalsIgnoreCase("startRecord")){
            initRecordingAgora();
            RecordingSDKInstance.registerOberserver(recordingEventHandler);

            String tmpEnv = System.getenv("KEEPMEDIATIME");
            if (tmpEnv != null && !tmpEnv.isEmpty()) {
                keepMediaTime = Integer.parseInt(tmpEnv);
                logger.info("Get system env:KEEPMEDIATIME " + keepMediaTime + " " + tmpEnv);
            } else {
                logger.info("No system env:KEEPMEDIATIME");
            }

           recordThread = new Thread(new CreatChannelRunnable(RecordingSDKInstance, channelId, uid, config, recordingEventHandler));
            recordThread.start();
            if (recordThread.isAlive()) {
                model.setMessage("Success");
            }
        }

        if (action.equalsIgnoreCase("pauseRecord")) {
            RecordingSDKInstance.stopService();
        }
        if(action.equalsIgnoreCase("stopRecord")){
            videoFileName = getVideoFileName(new File(storageDir));
            audioFileName = getAudioFileName(new File(storageDir));
            logger.info("File name : " + videoFileName + " "+ audioFileName + " UID:" + uid);
            String path = storageDir+videoFileName;

            String newPath = path.replace(CommonUtil.getPropertyParams("upload.folder"), "");
            try {
                checkupDetailBoProxy.editVideoRm(idDetailCheckup, newPath);
                model.setMessage("Success");
            } catch (GeneralBOException e) {
                logger.error("[AntrianOnlineController.getAntrianAll] Error get antrian all " + e.getMessage());
                throw new GeneralBOException("[AntrianOnlineController.getAntrianAll] Error When Error get antrian all");
            }

//            RecordingEngineProperties recordingEngineProperties = RecordingSDKInstance.getProperties();
            boolean isLeave = RecordingSDKInstance.leaveChannel();
            logger.info("Channel leave : " + isLeave);
            RecordingSDKInstance.unRegisterOberserver(recordingEventHandler);
        }

        logger.info("[AntrianOnlineController.create] end process POST / <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }

    private boolean IsMixMode() {
        return isMixMode;
    }

    protected void clean() {
        synchronized(this) {
            long now = System.currentTimeMillis();

            Iterator<Map.Entry<String, UserInfo>> audio_it = audioChannels.entrySet().iterator();
            while(audio_it.hasNext()) {
                Map.Entry<String, UserInfo> entry = audio_it.next();
                UserInfo info = entry.getValue();
                if(now - info.last_receive_time > 3000) {
                    try{
                        info.channel.close();
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
                    audio_it.remove();
                }
            }
            Iterator<Map.Entry<String, UserInfo>> video_it = videoChannels.entrySet().iterator();
            while(video_it.hasNext()) {
                Map.Entry<String, UserInfo> entry = video_it.next();
                UserInfo info = entry.getValue();
                if(now - info.last_receive_time > 3000 ) {
                    try{
                        info.channel.close();
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
                    video_it.remove();
                }
            }
        }
        cleanTimer.schedule(new RecordingCleanTimer(this), 10000);
    }

    private void checkUser(long uid, boolean isAudio) {
        String path = storageDir + Long.toString(uid);
        String key = Long.toString(uid);
        synchronized(this) {
            if(isAudio && !audioChannels.containsKey(key)) {
                if(config.decodeAudio == Common.AUDIO_FORMAT_TYPE.AUDIO_FORMAT_AAC_FRAME_TYPE ||
                        config.decodeAudio == Common.AUDIO_FORMAT_TYPE.AUDIO_FORMAT_PCM_FRAME_TYPE ||
                        config.decodeAudio == Common.AUDIO_FORMAT_TYPE.AUDIO_FORMAT_MIXED_PCM_FRAME_TYPE) {
                    String audioPath;
                    if(config.decodeAudio == Common.AUDIO_FORMAT_TYPE.AUDIO_FORMAT_AAC_FRAME_TYPE) {
                        audioPath = path + ".aac";
                    }else {
                        audioPath = path + ".pcm";
                    }
                    try {
                        UserInfo info = new UserInfo();
                        info.fileName = audioPath;
                        info.channel = new FileOutputStream(audioPath, true);
                        info.last_receive_time = System.currentTimeMillis();
                        audioChannels.put(key, info);
                    } catch(FileNotFoundException e) {
                        System.out.println("Can't find file : " + audioPath);
                    }
                }
            }

            if (!isAudio && !videoChannels.containsKey(key)) {
                if (config.decodeVideo == Common.VIDEO_FORMAT_TYPE.VIDEO_FORMAT_YUV_FRAME_TYPE
                        || config.decodeVideo == Common.VIDEO_FORMAT_TYPE.VIDEO_FORMAT_H264_FRAME_TYPE
                        || config.decodeVideo == Common.VIDEO_FORMAT_TYPE.VIDEO_FORMAT_ENCODED_FRAME_TYPE) {
                    String videoPath;
                    if (config.decodeVideo == Common.VIDEO_FORMAT_TYPE.VIDEO_FORMAT_ENCODED_FRAME_TYPE
                            || config.decodeVideo == Common.VIDEO_FORMAT_TYPE.VIDEO_FORMAT_H264_FRAME_TYPE) {
                        videoPath = path + ".h264";
                    } else {
                        videoPath = path + ".yuv";
                    }
                    try {
                        UserInfo info = new UserInfo();
                        info.fileName = videoPath;
                        info.channel = new FileOutputStream(videoPath, true);
                        info.last_receive_time = System.currentTimeMillis();
                        videoChannels.put(key, info);
                    } catch (FileNotFoundException e) {
                        System.out.println("Can't find file : " + videoPath);
                    }
                }
            }
        }
    }

    private String getVideoFileName(final File folder) {
        String fileName = "";
        for (final File fileEntry : folder.listFiles()) {
           if (fileEntry.getName().contains(".mp4")) {
               fileName = fileEntry.getName();
               break;
           }
        }
        return fileName;
    }

    private String getAudioFileName(final File folder) {
        String fileName = "";
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.getName().contains(".aac")) {
                fileName = fileEntry.getName();
                break;
            }
        }
        return fileName;

    }

    private int SetVideoMixingLayout() {
        Common ei = new Common();
        Common.VideoMixingLayout layout = ei.new VideoMixingLayout();
        layout.keepLastFrame = this.keepLastFrame;
        int max_peers = profile_type == Common.CHANNEL_PROFILE_TYPE.CHANNEL_PROFILE_COMMUNICATION ? 7:17;
        if(m_peers.size() > max_peers) {
            System.out.println("peers size is bigger than max m_peers:" + m_peers.size());
            return -1;
        }

        if (!IsMixMode()) {
            return -1;
        }

        long maxuid = 0;
        if (userAccount.length() > 0) {
            maxuid = RecordingSDKInstance.getUidByUserAccount(maxResolutionUserAccount);
        } else {
            maxuid = maxResolutionUid;
        }

        Vector<Long> videoUids = new Vector<Long>();
        Iterator it = m_peers.iterator();
        while(it.hasNext()) {
            Long uid = (Long)it.next();
            if (!config.autoSubscribe && !subscribedVideoUids.contains(uid))
                continue;
            if (layoutMode == VERTICALPRESENTATION_LAYOUT) {
                String uc = RecordingSDKInstance.getUserAccountByUid((int)(long)uid);
                if (uc.length() > 0 || maxuid != 0) {
                    videoUids.add(uid);
                }
            } else {
                videoUids.add(uid);
            }
        }

        layout.canvasHeight = height;
        layout.canvasWidth = width;
        layout.backgroundColor = "#23b9dc";
        layout.regionCount = (int) (videoUids.size());

        if (!videoUids.isEmpty()) {
            System.out.println("java setVideoMixingLayout videoUids is not empty, start layout");
            Common.VideoMixingLayout.Region[] regionList = new Common.VideoMixingLayout.Region[videoUids.size()];
            System.out.println("mixing layout mode:"+layoutMode);
            if(layoutMode == BESTFIT_LAYOUT) {
                adjustBestFitVideoLayout(regionList, layout, videoUids);
            }else if(layoutMode == VERTICALPRESENTATION_LAYOUT) {
                adjustVerticalPresentationLayout(maxuid, regionList, layout, videoUids);
            }else {
                adjustDefaultVideoLayout(regionList, layout, videoUids);
            }

            layout.regions = regionList;

        } else {
            layout.regions = null;
        }

        return RecordingSDKInstance.setVideoMixingLayout(layout);
    }

    private void adjustVerticalPresentationLayout(long maxResolutionUid, Common.VideoMixingLayout.Region[] regionList, Common.VideoMixingLayout layout, Vector<Long> videoUids) {
        System.out.println("begin adjust vertical presentation layout,peers size:" + videoUids.size()+", maxResolutionUid:" + maxResolutionUid);
        if(videoUids.size() <= 5) {
            adjustVideo5Layout(maxResolutionUid, regionList, layout, videoUids);
        }else if(videoUids.size() <= 7) {
            adjustVideo7Layout(maxResolutionUid, regionList, layout, videoUids);
        }else if(videoUids.size() <= 9) {
            adjustVideo9Layout(maxResolutionUid, regionList, layout, videoUids);
        }else {
            adjustVideo17Layout(maxResolutionUid, regionList, layout, videoUids);
        }
    }

    private void adjustBestFitVideoLayout(Common.VideoMixingLayout.Region[] regionList, Common.VideoMixingLayout layout, Vector<Long> videoUids) {
        if(videoUids.size() == 1) {
            adjustBestFitLayout_Square(regionList,1, layout, videoUids);
        }else if(videoUids.size() == 2) {
            adjustBestFitLayout_2(regionList, layout, videoUids);
        }else if( 2 < videoUids.size() && videoUids.size() <=4) {
            adjustBestFitLayout_Square(regionList,2, layout, videoUids);
        }else if(5<=videoUids.size() && videoUids.size() <=9) {
            adjustBestFitLayout_Square(regionList,3, layout, videoUids);
        }else if(10<=videoUids.size() && videoUids.size() <=16) {
            adjustBestFitLayout_Square(regionList,4, layout, videoUids);
        }else if(videoUids.size() ==17) {
            adjustBestFitLayout_17(regionList, layout, videoUids);
        }else {
            System.out.println("adjustBestFitVideoLayout is more than 17 users");
        }
    }

    private void adjustBestFitLayout_2(Common.VideoMixingLayout.Region[] regionList, Common.VideoMixingLayout layout, Vector<Long> videoUids) {
        float canvasWidth = (float)width;
        float canvasHeight = (float)height;
        float viewWidth = 0.235f;
        float viewHEdge = 0.012f;
        float viewHeight = viewWidth * (canvasWidth/canvasHeight);
        float viewVEdge = viewHEdge * (canvasWidth/canvasHeight);
        int peersCount = videoUids.size();
        for (int i=0; i < peersCount; i++) {
            regionList[i] = layout.new Region();
            regionList[i].uid = videoUids.get(i);
            regionList[i].x = (((i+1)%2) == 0) ?0:0.5;
            regionList[i].y =  0.f;
            regionList[i].width = 0.5f;
            regionList[i].height = 1.f;
            regionList[i].alpha = i+1;
            regionList[i].renderMode = 0;
        }
    }
    private void adjustDefaultVideoLayout(Common.VideoMixingLayout.Region[] regionList, Common.VideoMixingLayout layout, Vector<Long> videoUids) {
        regionList[0] = layout.new Region();
        regionList[0].uid = videoUids.get(0);
        regionList[0].x = 0.f;
        regionList[0].y = 0.f;
        regionList[0].width = 1.f;
        regionList[0].height = 1.f;
        regionList[0].alpha = 1.f;
        regionList[0].renderMode = 0;
        float f_width = width;
        float f_height = height;
        float canvasWidth = f_width;
        float canvasHeight = f_height;
        float viewWidth = 0.235f;
        float viewHEdge = 0.012f;
        float viewHeight = viewWidth * (canvasWidth / canvasHeight);
        float viewVEdge = viewHEdge * (canvasWidth / canvasHeight);
        for (int i = 1; i < videoUids.size(); i++) {
            regionList[i] = layout.new Region();

            regionList[i].uid = videoUids.get(i);
            float f_x = (i - 1) % 4;
            float f_y = (i - 1) / 4;
            float xIndex = f_x;
            float yIndex = f_y;
            regionList[i].x = xIndex * (viewWidth + viewHEdge) + viewHEdge;
            regionList[i].y = 1 - (yIndex + 1) * (viewHeight + viewVEdge);
            regionList[i].width = viewWidth;
            regionList[i].height = viewHeight;
            regionList[i].alpha = (i + 1);
            regionList[i].renderMode = 0;
        }
        layout.regions = regionList;
    }

    private void setMaxResolutionUid(int number, long maxResolutionUid, Common.VideoMixingLayout.Region[] regionList, double weight_ratio) {
        regionList[number].uid = maxResolutionUid;
        regionList[number].x = 0.f;
        regionList[number].y = 0.f;
        regionList[number].width = 1.f * weight_ratio;
        regionList[number].height = 1.f;
        regionList[number].alpha = 1.f;
        regionList[number].renderMode = 1;
    }
    private void changeToVideo7Layout(long maxResolutionUid, Common.VideoMixingLayout.Region[] regionList, Common.VideoMixingLayout layout, Vector<Long> videoUids) {
        System.out.println("changeToVideo7Layout");
        adjustVideo7Layout(maxResolutionUid, regionList, layout, videoUids);
    }
    private void changeToVideo9Layout(long maxResolutionUid, Common.VideoMixingLayout.Region[] regionList, Common.VideoMixingLayout layout, Vector<Long> videoUids) {
        System.out.println("changeToVideo9Layout");
        adjustVideo9Layout(maxResolutionUid, regionList, layout, videoUids);
    }
    private void changeToVideo17Layout(long maxResolutionUid, Common.VideoMixingLayout.Region[] regionList, Common.VideoMixingLayout layout, Vector<Long> videoUids) {
        System.out.println("changeToVideo17Layout");
        adjustVideo17Layout(maxResolutionUid, regionList, layout, videoUids);
    }
    private void adjustBestFitLayout_Square(Common.VideoMixingLayout.Region[] regionList, int nSquare, Common.VideoMixingLayout layout, Vector<Long> videoUids) {
        float canvasWidth = (float)width;
        float canvasHeight = (float)height;
        float viewWidth = (float)(1.f * 1.0/nSquare);
        float viewHEdge = (float)(1.f * 1.0/nSquare);
        float viewHeight = viewWidth * (canvasWidth/canvasHeight);
        float viewVEdge = viewHEdge * (canvasWidth/canvasHeight);
        int peersCount = videoUids.size();
        for (int i=0; i < peersCount; i++) {
            regionList[i] = layout.new Region();
            float xIndex =(float)(i%nSquare);
            float yIndex = (float)(i/nSquare);
            regionList[i].uid = videoUids.get(i);
            regionList[i].x = 1.f * 1.0/nSquare * xIndex;
            regionList[i].y = 1.f * 1.0/nSquare * yIndex;
            regionList[i].width = viewWidth;
            regionList[i].height = viewHEdge;
            regionList[i].alpha = (double)(i+1);
            regionList[i].renderMode = 0;
        }
    }
    private void adjustBestFitLayout_17(Common.VideoMixingLayout.Region[] regionList, Common.VideoMixingLayout layout, Vector<Long> videoUids) {
        float canvasWidth = (float)width;
        float canvasHeight = (float)height;
        int n = 5;
        float viewWidth = (float)(1.f * 1.0/n);
        float viewHEdge = (float)(1.f * 1.0/n);
        float totalWidth = (float)(1.f - viewWidth);
        float viewHeight = viewWidth * (canvasWidth/canvasHeight);
        float viewVEdge = viewHEdge * (canvasWidth/canvasHeight);
        int peersCount = videoUids.size();
        for (int i = 0; i < peersCount; i++) {
            regionList[i] = layout.new Region();
            float xIndex = (float)(i%(n-1));
            float yIndex = (float)(i/(n-1));
            regionList[i].uid = videoUids.get(i);
            regionList[i].width = viewWidth;
            regionList[i].height = viewHEdge;
            regionList[i].alpha = i+1;
            regionList[i].renderMode = 0;
            if(i == 16) {
                regionList[i].x = (1-viewWidth)*(1.f/2) * 1.f;
                System.out.println( "special layout for 17 x is:"+regionList[i].x);
            }else {
                regionList[i].x = 0.5f * viewWidth +  viewWidth * xIndex;
            }
            regionList[i].y =  (1.0/n) * yIndex;
        }
    }
    private void adjustVideo5Layout(long maxResolutionUid, Common.VideoMixingLayout.Region[] regionList, Common.VideoMixingLayout layout, Vector<Long> videoUids) {
        boolean flag = false;

        float canvasWidth = (float)width;
        float canvasHeight = (float)height;

        float viewWidth = 0.235f;
        float viewHEdge = 0.012f;
        float viewHeight = viewWidth * (canvasWidth/canvasHeight);
        float viewVEdge = viewHEdge * (canvasWidth/canvasHeight);
        int number = 0;

        int i=0;
        for (; i<videoUids.size(); i++) {
            regionList[i] = layout.new Region();
            if(maxResolutionUid == videoUids.get(i)){
                System.out.println("adjustVideo5Layout equal with configured user uid:" + maxResolutionUid);
                flag = true;
                setMaxResolutionUid(number,  maxResolutionUid, regionList,0.8);
                number++;
                continue;
            }
            regionList[number].uid = videoUids.get(i);
            //float xIndex = ;
            float yIndex = flag?((float)(number-1 % 4)):((float)(number % 4));
            regionList[number].x = 1.f * 0.8;
            regionList[number].y = (0.25) * yIndex;
            regionList[number].width = 1.f*(1-0.8);
            regionList[number].height = 1.f * (0.25);
            regionList[number].alpha = (double)number;
            regionList[number].renderMode = 0;
            number++;
            if(i == 4 && !flag){
                changeToVideo7Layout(maxResolutionUid, regionList, layout, videoUids);
            }
        }
    }



    private void adjustVideo7Layout(long maxResolutionUid, Common.VideoMixingLayout.Region[] regionList, Common.VideoMixingLayout layout, Vector<Long> videoUids) {
        boolean flag = false;
        float canvasWidth = (float)width;
        float canvasHeight = (float)height;

        float viewWidth = 0.235f;
        float viewHEdge = 0.012f;
        float viewHeight = viewWidth * (canvasWidth/canvasHeight);
        float viewVEdge = viewHEdge * (canvasWidth/canvasHeight);
        int number = 0;

        int i=0;
        for (; i<videoUids.size(); i++) {
            regionList[i] = layout.new Region();
            if(maxResolutionUid == videoUids.get(i)){
                System.out.println("adjustVideo7Layout equal with configured user uid:" + maxResolutionUid);
                flag = true;
                setMaxResolutionUid(number,  maxResolutionUid, regionList,6.f/7);
                number++;
                continue;
            }
            regionList[number].uid = videoUids.get(i);
            float yIndex = flag?((float)number-1 % 6):((float)(number % 6));
            regionList[number].x = 6.f/7;
            regionList[number].y = (1.f/6) * yIndex;
            regionList[number].width = (1.f/7);
            regionList[number].height = (1.f/6);
            regionList[number].alpha = (double)number;
            regionList[number].renderMode = 0;
            number++;
            if(i == 6 && !flag){
                changeToVideo9Layout(maxResolutionUid, regionList, layout, videoUids);
            }
        }

    }
    private void adjustVideo9Layout(long maxResolutionUid, Common.VideoMixingLayout.Region[] regionList, Common.VideoMixingLayout layout, Vector<Long> videoUids) {
        boolean flag = false;

        float canvasWidth = (float)width;
        float canvasHeight = (float)height;

        float viewWidth = 0.235f;
        float viewHEdge = 0.012f;
        float viewHeight = viewWidth * (canvasWidth/canvasHeight);
        float viewVEdge = viewHEdge * (canvasWidth/canvasHeight);
        int number = 0;

        int i=0;
        for (; i<videoUids.size(); i++) {
            regionList[i] = layout.new Region();
            if(maxResolutionUid == videoUids.get(i)){
                System.out.println("adjustVideo9Layout equal with configured user uid:" + maxResolutionUid);
                flag = true;
                setMaxResolutionUid(number,  maxResolutionUid, regionList,9.f/5);
                number++;
                continue;
            }
            regionList[number].uid = videoUids.get(i);
            float yIndex = flag?((float)(number-1 % 8)):((float)(number % 8));
            regionList[number].x = 8.f/9;
            regionList[number].y = (1.f/8) * yIndex;
            regionList[number].width = 1.f/9 ;
            regionList[number].height = 1.f/8;
            regionList[number].alpha = (double)number;
            regionList[number].renderMode = 0;
            number++;
            if(i == 8 && !flag){
                changeToVideo17Layout(maxResolutionUid, regionList, layout, videoUids);
            }
        }
    }

    private void adjustVideo17Layout(long maxResolutionUid, Common.VideoMixingLayout.Region[] regionList, Common.VideoMixingLayout layout, Vector<Long> videoUids) {
        boolean flag = false;
        float canvasWidth = (float)width;
        float canvasHeight = (float)height;

        float viewWidth = 0.235f;
        float viewHEdge = 0.012f;
        float viewHeight = viewWidth * (canvasWidth/canvasHeight);
        float viewVEdge = viewHEdge * (canvasWidth/canvasHeight);
        int number = 0;
        System.out.println("adjustVideo17Layoutenter videoUids size is:" + videoUids.size() + ", maxResolutionUid:" + maxResolutionUid);
        for (int i=0; i<videoUids.size(); i++) {
            regionList[i] = layout.new Region();
            if(maxResolutionUid == videoUids.get(i)){
                flag = true;
                setMaxResolutionUid(number,  maxResolutionUid, regionList,0.8);
                number++;
                continue;
            }
            if(!flag && i == 16) {
                System.out.println("Not the configured uid, and small regions is sixteen, so ignore this user:" + videoUids.get(i));
                break;
            }

            regionList[number].uid = videoUids.get(i);
            //float xIndex = 0.833f;
            float yIndex = flag?((float)((number-1) % 8)):((float)(number % 8));
            regionList[number].x = ((flag && i>8) || (!flag && i >=8)) ? (9.f/10):(8.f/10);
            regionList[number].y = (1.f/8) * yIndex;
            regionList[number].width =  1.f/10 ;
            regionList[number].height = 1.f/8;
            regionList[number].alpha = (double)number;
            regionList[number].renderMode = 0;
            number++;
        }
    }

    private void WriteBytesToFileClassic(long uid, byte[] byteBuffer, long size, boolean isAudio) {
        if (byteBuffer == null) {
            System.out.println("WriteBytesToFileClassic but byte buffer is null!");
            return;
        }
        synchronized(this) {
            try {
                UserInfo info = isAudio ? audioChannels.get(Long.toString(uid)) : videoChannels.get(Long.toString(uid));
                if(info != null) {
                    long curTs = System.currentTimeMillis();
                    if (isAudio) {
                        if (keepMediaTime > 0 && (curTs - lastKeepAudioTime)/1000 >= keepMediaTime) {
                            // System.out.printf("rewrite audio file:%s\n", info.fileName);
                            info.channel.close();
                            info.channel = new FileOutputStream(info.fileName, false);
                            lastKeepAudioTime = curTs;
                        }
                    } else {
                        if (keepMediaTime > 0 && (curTs - lastKeepVideoTime)/1000 >= keepMediaTime) {
                            // System.out.printf("rewrite video file:%s\n", info.fileName);
                            info.channel.close();
                            info.channel = new FileOutputStream(info.fileName, false);
                            lastKeepVideoTime = curTs;
                        }
                    }
                    info.channel.write(byteBuffer, 0, (int) size);
                    info.channel.flush();
                    info.last_receive_time = System.currentTimeMillis();
                } else {
                    System.out.println("Channel is null");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String GetNowDate() {
        String temp_str = "";
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        temp_str = sdf.format(dt);
        return temp_str;
    }

    private void PrintUsersInfo(Vector vector) {
        System.out.println("user size:" + vector.size());
        for (Long l : m_peers) {
            System.out.println("user:" + l);
        }
    }

    private boolean checkEnumValue(int val, int max, String msg) {
        if (val < 0 || val > max) {
            System.out.println(msg);
            return false;
        }
        return true;
    }

    private void initRecordingAgora() {
        RecordingSDK recordingSDK = new RecordingSDK();
        RecordingSDKInstance = recordingSDK;
        config = new RecordingConfig();
        config.channelProfile = Common.CHANNEL_PROFILE_TYPE.CHANNEL_PROFILE_COMMUNICATION;
        config.appliteDir =  CommonUtil.getPropertyParams("upload.folder") + CommonConstant.AGORA_DIR;
        config.triggerMode = 0;
        config.recordFileRootDir = CommonUtil.getPropertyParams("upload.folder") + CommonConstant.RESOURCE_PATH_VIDEO_RM;
        config.idleLimitSec = 5 * 60;
        config.isVideoOnly = false;
        config.isAudioOnly = false;
        config.isMixingEnabled = true;
        config.mixResolution = "640,360,15,500";
        config.mixedVideoAudio = Common.MIXED_AV_CODEC_TYPE.values() [Common.MIXED_AV_CODEC_TYPE.MIXED_AV_CODEC_V2.ordinal()];
        config.cfgFilePath = "/uid/";
        config.secret = "";
        config.decryptionMode = "";
        config.lowUdpPort = 40000;
        config.highUdpPort = 41000;
        config.captureInterval = 5;
        config.audioIndicationInterval = 0;
        config.decodeAudio = Common.AUDIO_FORMAT_TYPE.values()[Common.AUDIO_FORMAT_TYPE.AUDIO_FORMAT_DEFAULT_TYPE.ordinal()];
        config.decodeVideo = Common.VIDEO_FORMAT_TYPE.values()[Common.VIDEO_FORMAT_TYPE.VIDEO_FORMAT_DEFAULT_TYPE.ordinal()];
        config.streamType = Common.REMOTE_VIDEO_STREAM_TYPE.values() [Common.REMOTE_VIDEO_STREAM_TYPE.REMOTE_VIDEO_STREAM_HIGH.ordinal()];
        config.proxyType = 1;
        config.proxyServer = "";
        config.audioProfile = 2;
        config.defaultVideoBgPath = "";
        config.defaultUserBgPath = "";
        config.autoSubscribe = true;
        config.enableCloudProxy = false;
        config.enableIntraRequest = true;
        config.subscribeVideoUids = "";
        config.subscribeAudioUids = "";
        config.enableH265Support = false;

        if (config.decodeVideo == Common.VIDEO_FORMAT_TYPE.VIDEO_FORMAT_ENCODED_FRAME_TYPE) {
            config.decodeVideo = Common.VIDEO_FORMAT_TYPE.VIDEO_FORMAT_H264_FRAME_TYPE;
        }

        if (!config.autoSubscribe) {
            if (config.subscribeVideoUids != null) {
                config.subscribeVideoUids = String.valueOf(config.subscribeVideoUids);
                String[] struids = config.subscribeVideoUids.split(",");
                for (int i = 0; i < struids.length; i++) {
                    if (userAccount.length() > 0) {
                        subscribedVideoUserAccount.add(struids[i]);
                    } else {
                        try {
                            subscribedVideoUids.add(Long.parseLong(struids[i]));
                        } catch (Exception e) {
                            //Ignore exception here.
                        }
                    }
                }
            }
            if (config.subscribeVideoUids != null)
                config.subscribeAudioUids = String.valueOf(config.subscribeAudioUids);
        }

        if(config.audioProfile > 2) config.audioProfile = 2;
        if(config.audioProfile < 0) config.audioProfile = 0;

        this.isMixMode = config.isMixingEnabled;
        this.profile_type = Common.CHANNEL_PROFILE_TYPE.values()[config.channelProfile.getValue()];
        if (config.isMixingEnabled && !config.isAudioOnly) {
            String[] sourceStrArray = config.mixResolution.split(",");
            if (sourceStrArray.length != 4) {
                logger.info("Illegal resolution:" + config.mixResolution);
            }
            this.width = Integer.valueOf(sourceStrArray[0]).intValue();
            this.height = Integer.valueOf(sourceStrArray[1]).intValue();
            this.fps = Integer.valueOf(sourceStrArray[2]).intValue();
            this.kbps = Integer.valueOf(sourceStrArray[3]).intValue();
        }

        recordingEventHandler = new RecordingEventHandler() {
            @Override
            public void onLeaveChannel(int reason) {
                logger.error("[AntrianOnlineController.record] onLeaveChannel : " + reason);
                videoFileName = getVideoFileName(new File(storageDir));
                audioFileName = getAudioFileName(new File(storageDir));
                logger.info("File name : " + videoFileName + " "+ audioFileName);
                String path = storageDir+videoFileName;

                String newPath = path.replace(CommonUtil.getPropertyParams("upload.folder"), "");
                try {
                    checkupDetailBoProxy.editVideoRm(idDetailCheckup, newPath);
                } catch (GeneralBOException e) {
                    logger.error("[AntrianOnlineController.getAntrianAll] Error get antrian all " + e.getMessage());
                    throw new GeneralBOException("[AntrianOnlineController.getAntrianAll] Error When Error get antrian all");
                }

            }

            @Override
            public void onError(int error, int stat_code) {
                logger.error("RecordingSDK onError,error:" + error + ",stat code:" + stat_code);

            }

            @Override
            public void onWarning(int warn) {
                logger.error("RecordingSDK onWarning,warn:" + warn);

            }

            @Override
            public void onJoinChannelSuccess(String channelId, long uid) {
                if(config.decodeAudio != Common.AUDIO_FORMAT_TYPE.AUDIO_FORMAT_DEFAULT_TYPE) {
                    cleanTimer.schedule(new RecordingCleanTimer(AntrianOnlineController.this), 10000);
                }
                logger.info("RecordingSDK joinChannel success, channelId:" + channelId +", uid:" + uid);
            }

            @Override
            public void onRemoteVideoStreamStateChanged(long uid, Common.REMOTE_STREAM_STATE state, Common.REMOTE_STREAM_STATE_CHANGED_REASON reason) {

            }

            @Override
            public void onRemoteAudioStreamStateChanged(long uid, Common.REMOTE_STREAM_STATE state, Common.REMOTE_STREAM_STATE_CHANGED_REASON reason) {

            }

            @Override
            public void onUserOffline(long uid, int reason) {
                CrudResponse crudResponse = new CrudResponse();
                m_peers.remove(uid);
                //PrintUsersInfo(m_peers);
                SetVideoMixingLayout();
                logger.info("RecordingSDK onUserOffline uid:" + uid + ",offline reason:" + reason);
                if (uid == 2) {
                    videoFileName = getVideoFileName(new File(storageDir));
                    audioFileName = getAudioFileName(new File(storageDir));
                    logger.info("File name : " + videoFileName + " "+ audioFileName + " UID:" + uid);
                    String path = storageDir+videoFileName;

                    String newPath = path.replace(CommonUtil.getPropertyParams("upload.folder"), "");
                    try {
                       crudResponse = telemedicBoProxy.insertVideoRm(idDetailCheckup, newPath, "raw");

                    } catch (GeneralBOException e) {
                        logger.error("[AntrianOnlineController.insertVideoRm] Error " + e.getMessage());
                        throw new GeneralBOException("[AntrianOnlineController.insertVideoRm] Error ");
                    }

                    RecordingEngineProperties recordingEngineProperties = recordingSDK.getProperties();
                    boolean isLeave = recordingSDK.leaveChannel();
                    recordingSDK.unRegisterOberserver(this);
                    logger.info("Channel leave : " + isLeave);

//                    if (crudResponse.getMsg().equalsIgnoreCase("Success")) {
//                        try {
//                           crudResponse = telemedicBoProxy.updateVideoRmOnDetailCheckup(idDetailCheckup, newPath);
//                           if (crudResponse.getMsg().equalsIgnoreCase("Success")) {
//                               recordingSDK.unRegisterOberserver(this);
//                           }
//                        } catch (GeneralBOException e) {
//                            logger.error("[AntrianOnlineController.insertVideoRm] Error " + e.getMessage());
//                            throw new GeneralBOException("[AntrianOnlineController.insertVideoRm] Error ");
//                        }
//                    } else recordingSDK.unRegisterOberserver(this);




                }
            }

            @Override
            public void onUserJoined(long uid, String recordingDir) {
                logger.info("onUserJoined uid:" + uid + ",recordingDir:" + recordingDir);
                storageDir = recordingDir;
                logger.info("File name : " + videoFileName + " "+ audioFileName + " UID:" + uid);
                m_peers.add(uid);
                //PrintUsersInfo(m_peers);
                // When the user joined, we can re-layout the canvas
                if (userAccount.length() > 0) {
                    if (layoutMode != VERTICALPRESENTATION_LAYOUT || RecordingSDKInstance.getUidByUserAccount(maxResolutionUserAccount) != 0) {
                        SetVideoMixingLayout();
                    }
                }
                else {
                    SetVideoMixingLayout();
                }
            }

            @Override
            public void onActiveSpeaker(long uid) {
                logger.info("User:"+uid+"is speaking");

            }

            @Override
            public void audioFrameReceived(long uid, Common.AudioFrame frame) {
                // System.out.println("java demo
                // audioFrameReceived,uid:"+uid+",type:"+type);
                byte[] buf = null;
                long size = 0;
                checkUser(uid, true);
                if (frame.type == Common.AUDIO_FRAME_TYPE.AUDIO_FRAME_RAW_PCM) {// pcm
                    buf = frame.pcm.pcmBuf;
                    size = frame.pcm.pcmBufSize;
                } else {// aac
                    buf = frame.aac.aacBuf;
                    size = frame.aac.aacBufSize;
                }
                WriteBytesToFileClassic(uid, buf, size, true);
            }

            @Override
            public void videoFrameReceived(long uid, int type, Common.VideoFrame frame, int rotation) {
                byte[] buf = null;
                long size = 0;
                checkUser(uid, false);
                // System.out.println("java demovideoFrameReceived,uid:"+uid+",type:"+type);

                if (type == 0) {// yuv
                    buf = frame.yuv.buf;
                    size = frame.yuv.bufSize;
                    if (buf == null) {
                        logger.info("java demo videoFrameReceived null");
                    }
                } else if (type == 1) {// h264
                    buf = frame.h264.buf;
                    size = frame.h264.bufSize;
                } else if (type == 2) {// h265
                    buf = frame.h265.buf;
                    size = frame.h265.bufSize;
                } else if (type == 3) { // jpg
                    String path = storageDir + Long.toString(uid) + System.currentTimeMillis() + ".jpg";
                    buf = frame.jpg.buf;
                    size = frame.jpg.bufSize;
                    try {
                        FileOutputStream channel = new FileOutputStream(path, true);
                        channel.write(buf, 0, (int) size);
                        channel.close();
                    } catch(Exception e ){
                        logger.info("Error write to " + path);
                    }
                    return;
                }
                WriteBytesToFileClassic(uid, buf, size, false);
            }

            @Override
            public void recordingPathCallBack(String path) {
                storageDir = path;
            }

            @Override
            public void onAudioVolumeIndication(Common.AudioVolumeInfo[] infos) {
                if(infos.length == 0)
                    return;

                for(int i = 0; i < infos.length; i++) {
                    logger.info("User:"+ Long.toString(infos[i].uid)+", audio volume:" + infos[i].volume);
                }
            }

            @Override
            public void onFirstRemoteVideoDecoded(long uid, int width, int height, int elapsed) {
                logger.info("onFirstRemoteVideoDecoded User:"+ Long.toString(uid)+", width:" + width
                        + ", height:" + height + ", elapsed:" + elapsed);
            }

            @Override
            public void onFirstRemoteAudioFrame(long uid, int elapsed) {
                logger.info("onFirstRemoteAudioFrame User:"+ Long.toString(uid)+", elapsed:" + elapsed);

            }

            @Override
            public void onReceivingStreamStatusChanged(boolean receivingAudio, boolean receivingVideo) {
                logger.info("pre receiving audio status is " + m_receivingAudio + ", now receiving audio status is " + receivingAudio);
                logger.info("pre receiving video status is " + m_receivingVideo + ", now receiving video  status is " + receivingVideo);
                m_receivingAudio = receivingAudio;
                m_receivingVideo = receivingVideo;
            }

            @Override
            public void onConnectionLost() {
                logger.info("connection is lost");
            }

            @Override
            public void onConnectionInterrupted() {
                logger.info("connection is interrupted");
            }

            @Override
            public void onRejoinChannelSuccess(String channelId, long uid) {
                logger.info("onRejoinChannelSuccess, channel id : " + channelId + ", uid: " + uid);

            }

            @Override
            public void onConnectionStateChanged(Common.CONNECTION_STATE_TYPE state, Common.CONNECTION_CHANGED_REASON_TYPE reason) {

            }

            @Override
            public void onRemoteVideoStats(long uid, Common.RemoteVideoStats stats) {

            }

            @Override
            public void onRemoteAudioStats(long uid, Common.RemoteAudioStats stats) {

            }

            @Override
            public void onRecordingStats(Common.RecordingStats stats) {
            }

            @Override
            public void onLocalUserRegistered(long uid, String userAccount) {
                logger.info("onLocalUserRegistered: " + uid + " => " + userAccount);

            }

            @Override
            public void onUserInfoUpdated(long uid, String userAccount) {
                logger.info("onUserInfoUpdated: " + uid + " => " + userAccount);
                if (subscribedVideoUserAccount.contains(userAccount)) {
                    subscribedVideoUids.add(uid);
                }
                SetVideoMixingLayout();
            }
        };
    }


}
