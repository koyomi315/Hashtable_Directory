package Hashtable_Directory;

public class Hashtable {
//    存储数据数组 现在初步设计为根据姓名->哈希函数->得出索引->存入号码 号码为11位 用String存储
    private String[] name_keys;
    private String[] name_values;
    private String[] tel_keys;
    private String[] tel_values;
//    统计哈希表内数据个数
    private int count;
//    设定哈希表初始长度
    private int length;

    /*
    构造函数 申请哈希表存储空间 初始化count、length
    */
    public Hashtable(int length){
        this.name_keys = new String[length];
        this.name_values = new String[length];
        this.tel_keys = new String[length];
        this.tel_values = new String[length];
        this.count = 0;
        this.length =length;
    }

    /*
    哈希函数 利用秦九韶算法 Unicode编码 最后取余
    */
    public int HashFunc(String str){
        long hashcode = 0;   //哈希函数中用来计算的数
        int index;      //计算出str的索引
        for(int i=0;i<str.length();i++){
            hashcode = 37 * hashcode + str.charAt(i);
        }
        index = (int)(hashcode % this.length);
        return index;
    }

    /*
    判断哈希表是否已满
    */
    private boolean isFull(){
        return this.count == this.length;
    }

    /*
    插入或修改哈希表内数据 初步设定解决冲突方法：线性探查法
    */
    public int name_Put(String key, String value){
        int index,i;    //index存放key值的理论索引 i用于解决冲突
//        将key放入哈希函数得出value理论的索引
        i = index = HashFunc(key);
//        如果index上无数据 则将value放到index上
        do {
            if(name_keys[i] == null){
                name_keys[i] = key;
                name_values[i] = value;
                return 0;
            }
            if(name_keys[i].equals(key)){
//                name_values[i] = value;   此处不再修改 将修改功能独立出来
                return 2;
            }
            i = (i + 1) % this.length;
        }while (i != index);
        return 1;
    }
    public int tel_Put(String key,String value){
        int index,i;
        i = index = HashFunc(key);
        do {
            if(tel_keys[i] == null){
                tel_keys[i] = key;
                tel_values[i] = value;
                return 0;
            }
            if(tel_keys[i].equals(key)){
                return 3;
            }
            i = (i + 1) % this.length;
        }while(i != index);
        return 1;
    }
    public int Put(String name,String tel){
        int status1, status2;
//        查看哈希表是否已满
        if(isFull()){
            System.out.println("哈希表已满");
            return 1;
        }
//        查看哈希表是否已存有待存元素
        if(name_SearchForBoolean(name)){
            System.out.println("已存入相同姓名的元素");
            return 2;
        }
        if(tel_SearchForBoolean(tel)){
            System.out.println("已存入相同电话号码的元素");
            return 3;
        }
        status1 = name_Put(name,tel);
        status2 = tel_Put(tel,name);
        this.count++;
        if(status1 != status2){
            System.out.println("ERROR!");
            return 4;
        }
        return status1;
    }

    /*
    查找哈希表内元素
    */

    //以姓名作为关键词查找电话号码 name->tel
    public String name_Search(String key){
        int index,pos;  //index存放key值的理论索引 pos用于指向冲突时查找
        String value;   //存放查找到的元素
        pos = index = HashFunc(key);
        value = "未查询到该记录";
        do {
            if(name_keys[pos] == null) //理论索引上不存在数据 则说明不存在该数据
                break;
            if(name_keys[pos].equals(key)){   //找到key值与查找的key值相同 说明查找到数据
                value = name_values[pos];
                break;
            }
            pos = (pos + 1) % this.length;
        }while(pos != index);
        return value;
    }

    //以电话号码作为关键词查找姓名 tel->name
    public String tel_Search(String key){
        int index,pos;
        String value;
        pos = index = HashFunc(key);
        value = "未查询到该记录";
        do {
            if(tel_keys[pos] == null)
                break;
            if (tel_keys[pos].equals(key)){
                value = tel_values[pos];
                break;
            }
            pos = (pos + 1) % this.length;
        }while(pos != index);
        return value;
    }

    public boolean name_SearchForBoolean(String key){
        int index,pos;
        pos = index = HashFunc(key);
        do {
            if(name_keys[pos] == null)
                return false;
            if(name_keys[pos].equals(key))
                return true;
            pos = (pos + 1) % this.length;
        }while(pos != index);
        return false;
    }

    public boolean tel_SearchForBoolean(String key){
        int index,pos;
        pos = index = HashFunc(key);
        do {
            if(tel_keys[pos] == null)
                return false;
            if(tel_keys[pos].equals(key))
                return true;
            pos = (pos + 1) % this.length;
        }while(pos != index);
        return false;
    }

    public void name_Change(String name,String new_tel){
        int index,pos;
        String old_tel = "";
        pos = index = HashFunc(name);
        do {
            if(name_keys[pos].equals(name)){
                old_tel = name_values[pos];
                name_values[pos] = new_tel;
                break;
            }
            pos = (pos + 1) % this.length;
        }while(pos != index);
       tel_Delete(old_tel);
       tel_Put(new_tel,name);
    }

    public void tel_Change(String tel,String new_name){
        int index,pos;
        String old_name = "";
        pos = index = HashFunc(tel);
        do {
            if(tel_keys[pos].equals(tel)){
                old_name = tel_values[pos];
                tel_values[pos] = new_name;
                break;
            }
            pos = (pos + 1) % this.length;
        }while(pos != index);
        name_Delete(old_name);
        name_Put(new_name,tel);
    }

    private void name_Delete(String name){
        int pos,index;
        pos = index = HashFunc(name);
        do {
            if(name_keys[pos].equals(name)){
                name_keys[pos] = null;
                name_values[pos] = null;
                return;
            }
            pos = (pos + 1) % this.length;
        }while(pos != index);
    }

    private void tel_Delete(String tel){
        int pos,index;
        pos = index = HashFunc(tel);
        do {
            if(tel_keys[pos].equals(tel)){
                tel_keys[pos] = null;
                tel_values[pos] = null;
                return;
            }
            pos = (pos + 1) % this.length;
        }while(pos != index);
    }
}

