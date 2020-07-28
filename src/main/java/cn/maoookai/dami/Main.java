package cn.maoookai.dami;

import net.mamoe.mirai.console.plugins.PluginBase;
import net.mamoe.mirai.message.GroupMessage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Random;

class Main extends PluginBase {

    /********����ʦ********/

    public static String[] SP = {"������칷", "������ľͯ��", "���������ͽ�", "�Է�һĿ��", "��Ӱ������", "��Թ����", "���˻Ĵ�֮��", "��������ǰ", "��������ͯ��", "�콣���Ĺ���", "�������㼧", "�������е�", "�����弧"};
    public static String[] SSR = {"���칷", "����ͯ��", "�Ĵ�֮��", "��ħ", "С¹��", "С¹��", "С¹��", "С¹��", "С¹��", "С¹��", "��ľͯ��", "���е�", "������", "һĿ��", "�����", "��ҹ��", "��", "�˰���", "ѩͯ��", "ɽ��", "����ǰ", "���ͽ�", "������", "����", "�ײ���", "��᪴���", "��֪��", "������", "��ҹ�漧", "���⾵", "��ͯ��", "Ե����", "��¹��ǰ" ,"������"};
    public static String[] SR = {"�һ���", "ѩŮ", "��ʹ��", "��ʹ��", "����", "Ȯ��", "��Ů", "��Ů��Ҷ", "�������", "����ʦ", "������", "�й�", "��˻�", "��Ѫ��", "����", "����ʦ", "ʳ����", "�弧", "����", "�û���", "����Ů", "����", "ӣ����", "�ݱ���", "���¸�", "����", "�෻��", "ҹ��", "��ͯ��", "��ͯ��", "������", "���㼧", "�", "�Խ�����", "ϻ����Ů", "����", "��Ŀ��", "׷����", "Ѭ", "��", "è�ƹ�", "춾ճ�", "һ��ľ��", "����ʦ", "����", "�ô���", "з��", "ֽ��", "����ͯ��", "����", "ЫŮ"};
    public static String[] R = {"��β��", "����ͯ��", "���㾫", "����è", "��è", "��ͯ", "ͯ��", "ͯŮ", "����", "�׹�ʦ", "ѻ�칷", "ʳ����", "��ʿ֮��", "��Ů", "�����ܵ�", "��������", "��ٸ", "��ʱ֮Ů", "����Сɮ", "����", "��ͼ", "�ܺ�", "ɽ��", "ө��", "ɽͯ", "����", "��", "���ܴ���", "������", "��ʦ"};
    public static String helpMessage = "���ܱ�\n�鿨������ʦ����\nʮ��������ʦʮ��\n��Ҫ��+xxx����ȡʽ�����������ֵĶ���\n/roll��ҡһ������\n/roll2��ҡ��������\n/yxh ���� �¼���Ӫ����������\n�������꣺��ʾ���յ�������\n������/ѧӢ���ɽ�ʰ�ÿ��һ��\n����+������ľ��ӣ����䷭��������\n���Ϲ��ܽ�Ⱥ�Ŀ��á�2020.7.21���¡�";

    /********���շ���********/

    public static String[] six_stars = {"����","��¶","�µ�","��Ӱ","���","�̶��","��","��","Ī˹����","������","������","��","��","˹����","����","�����","����","ҹݺ","����","��������","���ŷ���","��ܽ����","�ƽ�֮��","����ʹ"};
    public static String[] five_stars = {"����","��������","�º�","ʯ��","����","����","��ɰ","����","��","�Һ�","���忨","έ��","����","������","�Ǽ�","���Ϳ�˹","ʫ����","ҹħ","ʳ����","ʨЫ","��","����","��ѩ","����","������","������˹","����","����","��","�ٹ�","������","��Ĭ","÷��","���","�����","����","�׽�","����","������","��������","ܽ����","�¿���˹","�ݶ�","�����^"};
    public static String[] four_stars = {"��","���ǿ�","�̵�","��","���ȶ�","÷","����","����","�ҽ���","������","������","�Է�","����","����","�ɫ","����","������","�Ƿ�","����ʦ","ĩҩ","����","��","Ľ˹","��˿���","˪Ҷ","����","�ű�","�춹","�����","Ѷʹ","��ѩ","����","������","Զɽ","ҹ��"};
    public static String[] three_stars = {"�ߵ�","���տ�","�¼�ҹ","�ձ�","����","ʷ������","������","ܽ��","����","�����ж�","����˿","�׸�³","���","õ��ɯ","����","���","��"};

    /********������Ϣ********/

    public static String[] summonMessageLibrary = {"���ܳ鵽SSR��", "�����������ô��", "����ʦ��Ҫ͵����", "�Ѿ�û����Ʊ�˰�", "����ˣ���鲻����", "���꣬��봸�648��", "�����������", "ʮ��R����", "�������Ѿ����û������"};
    public static String[] summonFailLibrary = {"��¥��һ��", "������¥�µ�ƨ��", "��һ�������ƨ", "��������ǩ", "�����Լ�1���ӵ�����", "ѯ����ҳ�", "��ɶ���Ԫ", "�ڴ��������"};
    public static String[] summonFailPlaceLibrary = {"����·��", "�������Ĺ�����", "����ӵĿڴ���", "һ����ˮ��", "�ߵ���ѧ�α���", "��İٶ�����", "��ʺ��ʱ��", "һ����������", "����ķ�����", "����A�εĿڴ���", "����", "Ⱥ�ļ���"};
    public static int[] diceNumber = {1, 2, 3, 4, 5, 6};
    public static boolean isUpEnabled = true;
    public static String baiduAppID = "20190228000271995";
    public static String baiduPrivateKey = "spvgmoXtsYjmvGFTbNZK";
    static String icibaUrl = "http://open.iciba.com/dsapi/";

    /********������********/

    static String currentSoulProvider() {
        String currentDay = currentWeekOfDayProvider();
        String currentSoul;
        switch (currentDay) {
            case "��һ":
                currentSoul = "ѩ�Ļꡢ�ز������ݡ����� + ����\n��ħ���죺����輧";
                break;
            case "�ܶ�":
                currentSoul = "����֮����ζ���в�è���� + �Ĺ���\n��ħ���죺����¥";
                break;
            case "����":
                currentSoul = "����֮ϻ����������Ħ�ޡ����� + �����\n��ħ���죺��֩��";
                break;
            case "����":
                currentSoul = "�������ۡ���������Ů + ����\n��ħ���죺������";
                break;
            case "����":
                currentSoul = "��Ů��ʱ�����������顢���� + ���\n��ħ���죺������";
                break;
            case "����":
            case "����":
                currentSoul = "�������ȫ�����꣨����������⣩\n��ħ���죺�ʳ�";
                break;
            default:
                currentSoul = "δ֪����";
                break;
        }
        return currentSoul;
    }

    static String currentWeekOfDayProvider() {
        Calendar calendar = Calendar.getInstance();
        String currentDayOfWeek;
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                currentDayOfWeek = "����";
                break;
            case 2:
                currentDayOfWeek = "��һ";
                break;
            case 3:
                currentDayOfWeek = "�ܶ�";
                break;
            case 4:
                currentDayOfWeek = "����";
                break;
            case 5:
                currentDayOfWeek = "����";
                break;
            case 6:
                currentDayOfWeek = "����";
                break;
            case 7:
                currentDayOfWeek = "����";
                break;
            default:
                currentDayOfWeek = "��֪�����ڼ�";
        }
        return currentDayOfWeek;
    }

    static int Counter(int total) {
        Random random = new Random();
        return random.nextInt(total);
    }

    static String summonMessage() {
        int result = Counter(summonMessageLibrary.length);
        return (summonMessageLibrary[result]);
    }

    static String summonFail() {
        int result = Counter(summonFailLibrary.length);
        return (summonFailLibrary[result]);
    }

    static String summonFailPlace() {
        int result = Counter(summonFailPlaceLibrary.length);
        return (summonFailPlaceLibrary[result]);
    }

    static String SSRPicker() {
        int result = Counter(SSR.length);
        return (SSR[result]);
    }

    static String SPPicker() {
        int result = Counter(SP.length);
        return (SP[result]);
    }

    static String SRPicker() {
        int result = Counter(SR.length);
        return (SR[result]);
    }

    static String RPicker() {
        int result = Counter(R.length);
        return (R[result]);
    }


    static String Six_Stars_Picker(){
        int result = Counter(six_stars.length);
        return ("�������"+'\t'+six_stars[result]);
    }

    static String Five_Stars_Picker(){
        int result = Counter(five_stars.length);
        return ("������"+'\t'+five_stars[result]);
    }

    static String Four_Stars_Picker(){
        int result = Counter(four_stars.length);
        return ("�����"+'\t'+four_stars[result]);
    }

    static String Three_Stars_Picker(){
        int result = Counter(three_stars.length);
        return ("����"+'\t'+three_stars[result]);
    }


    /********�鿨����********/

    static String OneShot() {
        Random gotCard = new Random();
        String oneResult;
        int result = gotCard.nextInt(1000);
        if (result < 787)
            oneResult = "R" + '\t' + RPicker();
        else if (result < 987)
            oneResult = "SR" + '\t' + SRPicker();
        else if (result < 997)
            oneResult = "SSR" + '\t' + SSRPicker();
        else
            oneResult = "SP" + '\t' + SPPicker();
        return oneResult;
    }

    static String OneShot2x() {
        Random gotCard = new Random();
        String oneResult;
        int result = gotCard.nextInt(1000);
        if (result < 762)
            oneResult = "R" + '\t' + RPicker();
        else if (result < 962)
            oneResult = "SR" + '\t' + SRPicker();
        else if (result < 987)
            oneResult = "SSR" + '\t' + SSRPicker();
        else
            oneResult = "SP" + '\t' + SPPicker();
        return oneResult;
    }

    static String OneShot_Arknights() {
        Random gotCard = new Random();
        String oneResult;
        int result = gotCard.nextInt(1000);
        if (result < 400)
            oneResult = Three_Stars_Picker();
        else if (result < 900)
            oneResult = Four_Stars_Picker();
        else if (result < 980)
            oneResult = Five_Stars_Picker();
        else
        oneResult = Six_Stars_Picker();
        return oneResult;
    }

    static String OneShot_HowManyTimes() {
        Random gotCard = new Random();
        String oneResult;
        int result = gotCard.nextInt(1000);
        if (result < 787)
            oneResult = RPicker();
        else if (result < 987)
            oneResult = SRPicker();
        else if (result < 997)
            oneResult = SSRPicker();
        else
            oneResult = SPPicker();
        return oneResult;
    }

    static String OneShot_HowManyTimes2x() {
        Random gotCard = new Random();
        String oneResult;
        int result = gotCard.nextInt(1000);
        if (result < 762)
            oneResult = RPicker();
        else if (result < 962)
            oneResult = SRPicker();
        else if (result < 987)
            oneResult = SSRPicker();
        else
            oneResult = SPPicker();
        return oneResult;
    }

    static String jsonHandler(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), StandardCharsets.UTF_8));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    /********�ʰ�ÿ��һ��********/

    static String getDailySentenceEnglish() {
        JSONObject getDailySentence = JSONObject.fromObject(jsonHandler(icibaUrl));
        return getDailySentence.getString("content");
    }

    static String getDailySentenceChinese() {
        JSONObject getDailySentence = JSONObject.fromObject(jsonHandler(icibaUrl));
        return getDailySentence.getString("note");
    }

    static String getCurrentDateFromDailySentence() {
        JSONObject getDailySentence = JSONObject.fromObject(jsonHandler(icibaUrl));
        return getDailySentence.getString("dateline");
    }

    /********�ٶȷ���********/

    private String createSign(String beingTranslated, String salt) throws NoSuchAlgorithmException {
        String text = baiduAppID + beingTranslated + salt + baiduPrivateKey;
        MessageDigest md5 = MessageDigest.getInstance("md5");
        byte[] b = text.getBytes();
        byte[] digest = md5.digest(b);
        char[] chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder sb = new StringBuilder();
        for (byte bb : digest) {
            sb.append(chars[(bb >> 4) & 15]);
            sb.append(chars[bb & 15]);
        }
        String result;
        result = sb.toString();
        return result;
    }

    static String translateResult(String url) {
        JSONObject getTranslateResult = JSONObject.fromObject(jsonHandler(url));
        String upperData = getTranslateResult.getString("trans_result");
        JSONArray lowerData = JSONArray.fromObject(upperData);
        JSONObject result = lowerData.getJSONObject(0);
        return result.getString("dst");
    }

    public void onLoad() {
        getLogger().info("Plugin loaded!");
    }

    public void onEnable() {
        this.getEventListener().subscribeAlways(GroupMessage.class, (event) -> {
            String msg = event.getMessage().contentToString();
            int repeatCalc = Counter(1000);

            if (msg.length() > 1) {
                if (msg.split("")[msg.length() - 1].equals("��") || msg.split("")[msg.length() - 2].equals("��")) {
                    int replyCalc = Counter(1000);
                    if (replyCalc <= 50) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        event.getGroup().sendMessage(msg.replace("��", "") + "!");
                    }
                }

                if (msg.split("")[msg.length() - 1].equals("��") || msg.split("")[msg.length() - 2].equals("��")) {
                    int replyCalc = Counter(1000);
                    if (replyCalc <= 50) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        event.getGroup().sendMessage(msg.replace("��", "") + "!");
                    }
                }
            }

            if (repeatCalc >= 998) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                event.getGroup().sendMessage(msg);
            }

            String result = null;
            String result1 = null;
            String result2 = null;
            String result3 = null;
            String result4 = null;
            String result5 = null;
            String result6 = null;
            String result7 = null;
            String result8 = null;
            String result9 = null;
            String result10 = null;

            if (msg.equals("�鿨")) {
                event.getGroup().sendMessage(summonMessage());
                try {
                    if (isUpEnabled)
                        result = OneShot2x();
                    else
                        result = OneShot();
                } catch (Exception e) {
                    e.printStackTrace();
                    event.getGroup().sendMessage(event.getSenderName() + e);
                }
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                event.getGroup().sendMessage(event.getSenderName() + "��鵽�� " + result);
            }


            if (msg.equals("ʮ��")) {
                event.getGroup().sendMessage(summonMessage());
                try {
                    if (isUpEnabled) {
                        result1 = OneShot2x();
                        result2 = OneShot2x();
                        result3 = OneShot2x();
                        result4 = OneShot2x();
                        result5 = OneShot2x();
                        result6 = OneShot2x();
                        result7 = OneShot2x();
                        result8 = OneShot2x();
                        result9 = OneShot2x();
                        result10 = OneShot2x();
                    } else {
                        result1 = OneShot();
                        result2 = OneShot();
                        result3 = OneShot();
                        result4 = OneShot();
                        result5 = OneShot();
                        result6 = OneShot();
                        result7 = OneShot();
                        result8 = OneShot();
                        result9 = OneShot();
                        result10 = OneShot();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    event.getGroup().sendMessage(event.getSenderName() + e);
                }
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                event.getGroup().sendMessage(event.getSenderName() + "���ʮ�����Ϊ... " + "\n" + result1 + "\n" + result2 + "\n" + result3 + "\n" + result4 + "\n" + result5 + "\n" + result6 + "\n" + result7 + "\n" + result8 + "\n" + result9 + "\n" + result10);
            }

            if (msg.equals("Ѱ��")){
                event.getGroup().sendMessage(summonMessage());
                try {
                    result = OneShot_Arknights();
                } catch (Exception e) {
                    e.printStackTrace();
                    event.getGroup().sendMessage(String.valueOf(e));
                }
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                event.getGroup().sendMessage(event.getSenderName()+ "��鵽�� " + result);
            }

            if (msg.equals("ʮ��Ѱ��")){
                event.getGroup().sendMessage(summonMessage());
                try {
                result1 = OneShot_Arknights();
                result2 = OneShot_Arknights();
                result3 = OneShot_Arknights();
                result4 = OneShot_Arknights();
                result5 = OneShot_Arknights();
                result6 = OneShot_Arknights();
                result7 = OneShot_Arknights();
                result8 = OneShot_Arknights();
                result9 = OneShot_Arknights();
                result10 = OneShot_Arknights();
            } catch (Exception e) {
                e.printStackTrace();
                    event.getGroup().sendMessage(event.getSenderName() + e);
            }
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                event.getGroup().sendMessage(event.getSenderName() + "���ʮ�����Ϊ... " + "\n" + result1 + "\n" + result2 + "\n" + result3 + "\n" + result4 + "\n" + result5 + "\n" + result6 + "\n" + result7 + "\n" + result8 + "\n" + result9 + "\n" + result10);
        }

            if (msg.equals("��������")) {
                event.getGroup().sendMessage("������" + currentWeekOfDayProvider() + "����ʮ���䣺" + currentSoulProvider());
            }

            if (msg.contains("/yxh")) {
                String[] splitString = msg.split("\\s+");
                try {
                    event.getGroup().sendMessage(splitString[1] + splitString[2] + "����ô�����أ�" + splitString[1] + "���Ŵ�Ҷ�����Ϥ������" + splitString[1] + splitString[2] + "����ô�����أ��������С������һ���˽�ɡ�" + '\n' + splitString[1] + splitString[2] + "��ʵ����" + splitString[1] + splitString[2] + "����ҿ��ܻ�ܾ���" + splitString[1] + "��ô��" + splitString[2] + "�أ�����ʵ����������С��Ҳ�е��ǳ����ȡ�" + '\n' + "����ǹ���" + splitString[1] + splitString[2] + "�������ˣ������ʲô�뷨�أ���ӭ������������С��һ������Ŷ��");
                } catch (Exception e) {
                    event.getGroup().sendMessage(event.getSenderName() + "����ĸ�ʽ����ȷ��");
                }
            }

            if (msg.equals("/help")) {
                event.getGroup().sendMessage(helpMessage);
            }

            if (msg.equals("roll")) {
                String rolledDiceNumber = String.valueOf(diceNumber[Counter(diceNumber.length)]);
                event.getGroup().sendMessage(event.getSenderName() + "��ҡ����" + rolledDiceNumber);
            }

            if (msg.equals("roll2")) {
                String rolledDiceNumber = String.valueOf(diceNumber[Counter(diceNumber.length)] + diceNumber[Counter(diceNumber.length)]);
                event.getGroup().sendMessage(event.getSenderName() + "��ҡ����" + rolledDiceNumber);
            }

            if (msg.contains("��Ҫ��")) {
                String ssrWanted = msg.split("��Ҫ��")[1];
                boolean SSRExists = false;
                for (String s : SSR) {
                    if (s.equals(ssrWanted)) {
                        SSRExists = true;
                        break;
                    }
                }
                for (String s : SR) {
                    if (s.equals(ssrWanted)) {
                        SSRExists = true;
                        break;
                    }
                }
                for (String s : R) {
                    if (s.equals(ssrWanted)) {
                        SSRExists = true;
                        break;
                    }
                }
                for (String s : SP) {
                    if (s.equals(ssrWanted)) {
                        SSRExists = true;
                        break;
                    }
                }
                if (SSRExists) {
                    event.getGroup().sendMessage(summonMessage());
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    boolean gotSSR = false;
                    int picks = 0;
                    while (! gotSSR) {
                        picks++;
                        if (isUpEnabled) {
                            if (OneShot_HowManyTimes2x().equals(ssrWanted))
                                gotSSR = true;
                        } else {
                            if (OneShot_HowManyTimes().equals(ssrWanted))
                                gotSSR = true;
                        }
                    }
                    event.getGroup().sendMessage(event.getSenderName() + "�㻨����" + picks + "�����صķ��䣬�����ٻ�����" + ssrWanted + "!");
                } else {
                    event.getGroup().sendMessage(event.getSenderName() + "��ͨ��" + summonFail() + "Ȼ����" + summonFailPlace() + "�����" + ssrWanted);
                }
            }

            if (msg.startsWith("����") && ! msg.equals("����")) {
                String beingTranslated = msg.replace("����", "");
                String salt = String.valueOf(Counter(114514));
                String sign = null;
                try {
                    sign = createSign(beingTranslated, salt);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                String preUrl1 = "https://api.fanyi.baidu.com/api/trans/vip/translate?q=" + beingTranslated + "&from=auto&to=zh&appid=" + baiduAppID + "&salt=" + salt + "&sign=" + sign;
                String preUrl2 = preUrl1.replace("+","");
                String url = preUrl2.replace(" ", "%20");
                System.out.println(url);
                String translateResult = translateResult(url);
                event.getGroup().sendMessage("��"+beingTranslated + "���������ǣ���" + translateResult+"��");
            }

            if (msg.contains("ѧӢ��") || msg.contains("������"))
                event.getGroup().sendMessage(getCurrentDateFromDailySentence() + " ÿ��һ��\n" + getDailySentenceEnglish() + '\n' + getDailySentenceChinese());

        });
    }

}          