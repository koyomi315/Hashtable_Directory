package Hashtable_Directory;

public class Hashtable {
//    存储数据数组 现在初步设计为根据姓名->哈希函数->得出索引->存入号码 号码为11位 用String存储
    private String[] keys;
    private String[] values;
//    统计哈希表内数据个数
    private int count;
//    设定哈希表初始长度
    private int length;

    /*
    构造函数 申请哈希表存储空间 初始化count、length
    */
    public Hashtable(int length){
        this.keys = new String[length];
        this.values = new String[length];
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
    public int Put(String key,String value){
        int index,i;    //index存放key值的理论索引 i用于解决冲突
//        将key放入哈希函数得出value理论的索引
        i = index = HashFunc(key);
//        查看哈希表是否已满
        if(isFull()){
            System.out.println("哈希表已满");
            return 1;
        }
//        查看哈希表是否已存有待存元素
        if(SearchForBoolean(key,value)){
            System.out.println("哈希表已存入相同元素");
            return 2;
        }
//        如果index上无数据 则将value放到index上
        do {
            if(keys[i] == null){
                keys[i] = key;
                values[i] = value;
                this.count ++;
                return 0;
            }
            if(keys[i].equals(key)){
                values[i] = value;
                return -1;
            }
            i = (i + 1) % this.length;
        }while (i != index);
        return 1;
    }

    /*
    查找哈希表内元素
    */
    public String Search(String key){
        int index,pos;  //index存放key值的理论索引 pos用于指向冲突时查找
        String value;   //存放查找到的元素
        pos = index = HashFunc(key);
        value = "NULL";
        do {
            if(keys[pos] == null) //理论索引上不存在数据 则说明不存在该数据
                break;
            if(keys[pos].equals(key)){   //找到key值与查找的key值相同 说明查找到数据
                value = values[pos];
                break;
            }
            pos ++;
        }while(pos != index);
        return value;
    }
    public boolean SearchForBoolean(String key,String value){
        String result = Search(key);
        return result.equals(value);
    }


}
