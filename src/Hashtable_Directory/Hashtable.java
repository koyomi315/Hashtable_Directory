package Hashtable_Directory;

public class Hashtable {
//    存储数据数组
    private final String[] name_keys;       //以用户名为关键字的键值对哈希表 keys = name
    private final String[] name_values;     //values = tel
    private final String[] tel_keys;        //以电话号码为关键字的键值对哈希表 keys = tel
    private final String[] tel_values;      //values = name
//    统计哈希表内数据个数
    private int count;
//    设定哈希表初始长度
    private final int length;

    /*
    构造函数 申请哈希表存储空间、初始化count、length
    */
    public Hashtable(int length){
        this.name_keys = new String[length];
        this.name_values = new String[length];
        this.tel_keys = new String[length];
        this.tel_values = new String[length];
        this.count = 0;
        this.length = length;
    }

    /*
    哈希函数 利用秦九韶算法 Unicode编码 最后取余
    */
    public int HashFunc(String str){
        long hashcode = 0;      //哈希函数中用来计算的数
        int index;              //计算出str的索引
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
    向以用户名为关键字的哈希表内插入数据 初步设定解决冲突方法：线性探查法
    */
    public int name_Put(String key, String value){
        int index,i;        //index存放key值的理论索引 i用于解决冲突
        i = index = HashFunc(key);
        do {
            //找到空位，插入数据
            if(name_keys[i] == null){
                name_keys[i] = key;
                name_values[i] = value;
                return 0;   //正常插入数据，返回值0
            }
            i = (i + 1) % this.length;
        }while (i != index);
        return 1;           //遍历哈希表仍无空间存储，说明哈希表已满，返回值1
    }

    /*
    向以电话号码为关键字的哈希表内插入数据
    */
    public int tel_Put(String key,String value){
        int index,i;        //index存放key值的理论索引，i用于解决冲突
        i = index = HashFunc(key);
        do {
            //找到空位，插入数据
            if(tel_keys[i] == null){
                tel_keys[i] = key;
                tel_values[i] = value;
                return 0;   //正常插入数据，返回值0
            }
            i = (i + 1) % this.length;
        }while(i != index);
        return 1;           //遍历哈希表仍无空间存储，说明哈希表已满，返回值1
    }

    /*
    向整体的哈希表内插入数据 新增数据
    */
    public int Put(String name,String tel){
        int status1, status2;
//        查看哈希表是否已满
        if(isFull()){
            System.out.println("哈希表已满");
            return 1;       //哈希表已满，返回值1
        }
//        查看哈希表是否已存有待存元素
        if(name_SearchForBoolean(name)){
            System.out.println("已存入相同姓名的元素");
            return 2;       //有姓名冲突，返回值2
        }
        if(tel_SearchForBoolean(tel)){
            System.out.println("已存入相同电话号码的元素");
            return 3;       //有电话号码冲突，返回值3
        }
        status1 = name_Put(name,tel);
        status2 = tel_Put(tel,name);
        this.count++;       //向整体哈希表插入一条数据，count+1
        if(status1 != status2){
            System.out.println("ERROR!");
            return 4;       //两个哈希表插入数据状态不同，返回值4
        }
        return status1;     //理应正常插入数据，返回值0
    }

    /*
    以姓名作为关键词查找电话号码 name->tel
    */
    public String name_Search(String key){
        int index,pos;  //index存放key值的理论索引 pos为遍历查找的指针
        String value;   //存放查找到的元素
        pos = index = HashFunc(key);
        value = "未查询到该记录";
        do {
            if(name_keys[pos] == null)          //理论索引上或从理论索引遍历到null，则说明不存在待查数据
                break;
            if(name_keys[pos].equals(key)){     //找到key值与查找的key值相同，说明查找到数据
                value = name_values[pos];
                break;
            }
            pos = (pos + 1) % this.length;
        }while(pos != index);
        return value;
    }

    /*
    以电话号码作为关键词查找姓名 tel->name
    */
    public String tel_Search(String key){
        int index,pos;  //index存放key值的理论索引 pos为遍历查找的指针
        String value;   //存放查找到的元素
        pos = index = HashFunc(key);
        value = "未查询到该记录";
        do {
            if(tel_keys[pos] == null)           //理论索引上或从理论索引遍历到null，则说明不存在待查数据
                break;
            if (tel_keys[pos].equals(key)){     //找到key值与查找的key值相同，说明查找到数据
                value = tel_values[pos];
                break;
            }
            pos = (pos + 1) % this.length;
        }while(pos != index);
        return value;
    }

    /*
    查找key与已存姓名是否冲突
    */
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

    /*
    查找key与已存电话号码是否冲突
    */
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

    /*
    以姓名作为关键字 修改已存数据的电话号码项
    */
    public void name_Change(String name,String new_tel){
        int index,pos;
        String old_tel = "";
        pos = index = HashFunc(name);
        do {
            if(name_keys[pos].equals(name)){    //找到姓名哈希表内待修改数据
                old_tel = name_values[pos];     //取出数据对应的原电话号码 作为修改电话号码哈希表时的索引
                name_values[pos] = new_tel;     //修改电话号码
                break;
            }
            pos = (pos + 1) % this.length;
        }while(pos != index);
        //由于不同电话号码通过哈希函数得到索引不同 我们需要删除电话号码哈希表旧数据 重新插入新数据
       tel_Delete(old_tel);                     //删除电话号码旧数据
       tel_Put(new_tel,name);                   //插入电话号码新数据
    }

    /*
    以电话号码作为关键字 修改已存数据的姓名项
    */
    public void tel_Change(String tel,String new_name){
        int index,pos;
        String old_name = "";
        pos = index = HashFunc(tel);
        do {
            if(tel_keys[pos].equals(tel)){  //找到电话号码哈希表内待修改数据
                old_name = tel_values[pos]; //取出数据对应的原姓名 作为修改姓名哈希表时的索引
                tel_values[pos] = new_name; //修改姓名
                break;
            }
            pos = (pos + 1) % this.length;
        }while(pos != index);
        //由于不同姓名通过哈希函数得到索引不同 我们需要删除姓名哈希表旧数据 重新插入新数据
        name_Delete(old_name);              //删除姓名哈希表旧数据
        name_Put(new_name,tel);             //插入姓名哈希表新数据
    }

    /*
    以姓名作为关键字 删除姓名哈希表上的数据（非传统意义删除数据功能 作为修改功能的插件）
    */
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

    /*
    以电话号码作为关键字 删除电话号码哈希表上的数据（非传统意义删除数据功能 作为修改功能的插件）
    */
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

