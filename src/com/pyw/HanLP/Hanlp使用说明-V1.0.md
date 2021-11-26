# 目录

* [1. HanLP的使用](#1-hanlp的使用)
  * [1.1 入门介绍](#11-入门介绍)
    * [1.1.1 官网地址](#111-官网地址)
    * [1.1.2 下载与配置](#112-下载与配置)
    * [1.1.3 功能概述](#113-功能概述)
  * [1.2 工具类](#12-工具类)
    * [1.2.1 标准分词](#121-标准分词)
    * [1.2.2 NLP分词](#122-nlp分词)
    * [1.2.3 索引分词](#123-索引分词)
    * [1.2.4 动态词典](#124-动态词典)
    * [1.2.5 关键词提取](#125-关键词提取)
    * [1.2.6 获取摘要](#126-获取摘要)
    * [1.2.7 获取文本拼音](#127-获取文本拼音)
    * [1.2.8 文本推荐](#128-文本推荐)
    * [1.2.9 文本相似度](#129-文本相似度)
    * [1.2.10 相似度匹配](#1210-相似度匹配)
    * [1.2.11 语义距离](#1211-语义距离)
    * [1.2.12 寻找近义词](#1212-寻找近义词)
    * [1.2.13 新词发现](#1213-新词发现)
    * [1.2.14 繁体简体转换](#1214-繁体简体转换)
    * [1.2.15 debug模式](#1215-debug模式)


# 1. HanLP的使用

## 1.1 入门介绍
### 1.1.1 官网地址
 HanLP的官网： https://github.com/hankcs/HanLP 

### 1.1.2 下载与配置

**方式一、Maven**  

为了方便用户，特提供内置了数据包的Portable版，只需在pom.xml加入：

```
<dependency>
    <groupId>com.hankcs</groupId>
    <artifactId>hanlp</artifactId>
    <version>portable-1.7.5</version>
</dependency>
```

零配置，即可使用基本功能（除由字构词、依存句法分析外的全部功能）。如果用户有自定义的需求，可以参考方式二，使用hanlp.properties进行配置（Portable版同样支持hanlp.properties）。



**方式二、下载jar、data、hanlp.properties**

HanLP将数据与程序分离，给予用户自定义的自由。

1、下载：[data.zip](http://nlp.hankcs.com/download.php?file=data)

下载后解压到任意目录，接下来通过配置文件告诉HanLP数据包的位置。

HanLP中的数据分为*词典*和*模型*，其中*词典*是词法分析必需的，*模型*是句法分析必需的。

```
data
│
├─dictionary
└─model
```

用户可以自行增删替换，如果不需要句法分析等功能的话，随时可以删除model文件夹。

- 模型跟词典没有绝对的区别，隐马模型被做成人人都可以编辑的词典形式，不代表它不是模型。
- GitHub代码库中已经包含了data.zip中的词典，直接编译运行自动缓存即可；模型则需要额外下载。

 2、下载jar和配置文件：[hanlp-release.zip](http://nlp.hankcs.com/download.php?file=jar)

配置文件的作用是告诉HanLP数据包的位置，只需修改第一行

```
root=D:/JavaProjects/HanLP/
```

为data的**父目录**即可，比如data目录是`/Users/hankcs/Documents/data`，那么`root=/Users/hankcs/Documents/` 。

最后将`hanlp.properties`放入classpath即可，对于多数项目，都可以放到src或resources目录下，编译时IDE会自动将其复制到classpath中。除了配置文件外，还可以使用环境变量`HANLP_ROOT`来设置`root`。安卓项目请参考[demo](https://github.com/hankcs/HanLPAndroidDemo)。

如果放置不当，HanLP会提示当前环境下的合适路径，并且尝试从项目根目录读取数据集。

### 1.1.3 功能概述


HanLP提供下列功能：

* 中文分词
  * `HMM-Bigram（速度与精度最佳平衡；一百兆内存）`
    * [最短路分词](https://github.com/hankcs/HanLP#1-%E7%AC%AC%E4%B8%80%E4%B8%AAdemo)、[N-最短路分词](https://github.com/hankcs/HanLP#5-n-%E6%9C%80%E7%9F%AD%E8%B7%AF%E5%BE%84%E5%88%86%E8%AF%8D)
  * 由字构词（侧重精度，全世界最大语料库，可识别新词；适合NLP任务）
    * [感知机分词](https://github.com/hankcs/HanLP/wiki/%E7%BB%93%E6%9E%84%E5%8C%96%E6%84%9F%E7%9F%A5%E6%9C%BA%E6%A0%87%E6%B3%A8%E6%A1%86%E6%9E%B6)、[CRF分词](https://github.com/hankcs/HanLP#6-crf%E5%88%86%E8%AF%8D)
  * 词典分词（侧重速度，每秒数千万字符；省内存）
    * [极速词典分词](https://github.com/hankcs/HanLP#7-%E6%9E%81%E9%80%9F%E8%AF%8D%E5%85%B8%E5%88%86%E8%AF%8D)
  * 所有分词器都支持：
    * [索引全切分模式](https://github.com/hankcs/HanLP#4-%E7%B4%A2%E5%BC%95%E5%88%86%E8%AF%8D)
    * [用户自定义词典](https://github.com/hankcs/HanLP#8-%E7%94%A8%E6%88%B7%E8%87%AA%E5%AE%9A%E4%B9%89%E8%AF%8D%E5%85%B8)
    * [兼容繁体中文](https://github.com/hankcs/HanLP/blob/master/src/test/java/com/hankcs/demo/DemoPerceptronLexicalAnalyzer.java#L29)
    * [训练用户自己的领域模型](https://github.com/hankcs/HanLP/wiki)
* 词性标注
  * [HMM词性标注](https://github.com/hankcs/HanLP/blob/master/src/main/java/com/hankcs/hanlp/seg/Segment.java#L584)（速度快）
  * [感知机词性标注](https://github.com/hankcs/HanLP/wiki/%E7%BB%93%E6%9E%84%E5%8C%96%E6%84%9F%E7%9F%A5%E6%9C%BA%E6%A0%87%E6%B3%A8%E6%A1%86%E6%9E%B6)、[CRF词性标注](https://github.com/hankcs/HanLP/wiki/CRF%E8%AF%8D%E6%B3%95%E5%88%86%E6%9E%90)（精度高）
* 命名实体识别
  * 基于HMM角色标注的命名实体识别 （速度快）
    * [中国人名识别](https://github.com/hankcs/HanLP#9-%E4%B8%AD%E5%9B%BD%E4%BA%BA%E5%90%8D%E8%AF%86%E5%88%AB)、[音译人名识别](https://github.com/hankcs/HanLP#10-%E9%9F%B3%E8%AF%91%E4%BA%BA%E5%90%8D%E8%AF%86%E5%88%AB)、[日本人名识别](https://github.com/hankcs/HanLP#11-%E6%97%A5%E6%9C%AC%E4%BA%BA%E5%90%8D%E8%AF%86%E5%88%AB)、[地名识别](https://github.com/hankcs/HanLP#12-%E5%9C%B0%E5%90%8D%E8%AF%86%E5%88%AB)、[实体机构名识别](https://github.com/hankcs/HanLP#13-%E6%9C%BA%E6%9E%84%E5%90%8D%E8%AF%86%E5%88%AB)
  * 基于线性模型的命名实体识别（精度高）
    * [感知机命名实体识别](https://github.com/hankcs/HanLP/wiki/%E7%BB%93%E6%9E%84%E5%8C%96%E6%84%9F%E7%9F%A5%E6%9C%BA%E6%A0%87%E6%B3%A8%E6%A1%86%E6%9E%B6)、[CRF命名实体识别](https://github.com/hankcs/HanLP/wiki/CRF%E8%AF%8D%E6%B3%95%E5%88%86%E6%9E%90)
* 关键词提取
  * [TextRank关键词提取](https://github.com/hankcs/HanLP#14-%E5%85%B3%E9%94%AE%E8%AF%8D%E6%8F%90%E5%8F%96)
* 自动摘要
  * [TextRank自动摘要](https://github.com/hankcs/HanLP#15-%E8%87%AA%E5%8A%A8%E6%91%98%E8%A6%81)
* 短语提取
  * [基于互信息和左右信息熵的短语提取](https://github.com/hankcs/HanLP#16-%E7%9F%AD%E8%AF%AD%E6%8F%90%E5%8F%96)
* [拼音转换](https://github.com/hankcs/HanLP#17-%E6%8B%BC%E9%9F%B3%E8%BD%AC%E6%8D%A2)
  * 多音字、声母、韵母、声调
* [简繁转换](https://github.com/hankcs/HanLP#18-%E7%AE%80%E7%B9%81%E8%BD%AC%E6%8D%A2)
  * 简繁分歧词（简体、繁体、臺灣正體、香港繁體）
* [文本推荐](https://github.com/hankcs/HanLP#19-%E6%96%87%E6%9C%AC%E6%8E%A8%E8%8D%90)
  * 语义推荐、拼音推荐、字词推荐
* 依存句法分析
  * [基于神经网络的高性能依存句法分析器](https://github.com/hankcs/HanLP#21-%E4%BE%9D%E5%AD%98%E5%8F%A5%E6%B3%95%E5%88%86%E6%9E%90)
  * [基于ArcEager转移系统的柱搜索依存句法分析器](https://github.com/hankcs/HanLP/blob/master/src/test/java/com/hankcs/demo/DemoDependencyParser.java#L34)
* [文本分类](https://github.com/hankcs/HanLP/wiki/%E6%96%87%E6%9C%AC%E5%88%86%E7%B1%BB%E4%B8%8E%E6%83%85%E6%84%9F%E5%88%86%E6%9E%90)
  * [情感分析](https://github.com/hankcs/HanLP/wiki/%E6%96%87%E6%9C%AC%E5%88%86%E7%B1%BB%E4%B8%8E%E6%83%85%E6%84%9F%E5%88%86%E6%9E%90#%E6%83%85%E6%84%9F%E5%88%86%E6%9E%90)
* [文本聚类](https://github.com/hankcs/HanLP/wiki/%E6%96%87%E6%9C%AC%E8%81%9A%E7%B1%BB)
  - KMeans、Repeated Bisection、自动推断聚类数目k
* [word2vec](https://github.com/hankcs/HanLP/wiki/word2vec)
  * 词向量训练、加载、词语相似度计算、语义运算、查询、KMeans聚类
  * 文档语义相似度计算
* [语料库工具](https://github.com/hankcs/HanLP/tree/master/src/main/java/com/hankcs/hanlp/corpus)
  - 部分默认模型训练自小型语料库，鼓励用户自行训练。所有模块提供[训练接口](https://github.com/hankcs/HanLP/wiki)，语料可参考[98年人民日报语料库](http://file.hankcs.com/corpus/pku98.zip)。

在提供丰富功能的同时，HanLP内部模块坚持低耦合、模型坚持惰性加载、服务坚持静态提供、词典坚持明文发布，使用非常方便。默认模型训练自全世界最大规模的中文语料库，同时自带一些语料处理工具，帮助用户训练自己的模型。


## 1.2 工具类

根据HanLP提供的接口，封装了一个com.ccxe.ims.utils.HanLPUtil：

### 1.2.1 标准分词
  * 标准分词使用```HanLP.newSegment()```获取当前版本最优分词器
  * 分词后返回每个词的文本和词性，默认不返回词在文本中出现的位置
  * 所有分词器最终都是返回一个```List<Term>```
  * 优势
    * 效率和效果的最佳平衡

```java_holder_method_tree
   /**                                                                                  
    * 标准分词                                                                              
    * 		标准分词默认不开启offset（词出现的位置）                                                      
    * 		getFrequency方法获取的词频是相对于整个词库 而不是当前分词的内容                                       
    * @param text 需要分词的内容    
    * @return Term集合 
    *         属性：   
    *         nature（词性，遵循https://www.hankcs.com/nlp/part-of-speech-tagging.html#h2-8）
    */ 
    public static List<Term> standardTokenizer(String text) {                            
        return StandardTokenizer.segment(text);                                          
    }                       
```

### 1.2.2 NLP分词
  * NLPTokenizer会执行词性标注和命名实体识别
  * 开启nlp分词需要下载额外数据包，jar包中只有词典，没有训练模型
  * 除标准分词以外的分词器都默认返回了```offset```(单词在文本中的偏移量)
  * 优势
    * 该分词器可以有效的分出新词汇
    * 使用自己的语料训练出的模型更符合对应场景

```java_holder_method_tree
    /**
     * NLP分词
     * 		NLPTokenizer会执行词性标注和命名实体识别
     * 注意：nlp分词需要下载数据包，数据包较大（600+MB）,如果要测试请自行下载数据包
     * @param text
     * @return
     */
    public static List<Term> nlpTokenizer(String text) {
        return NLPTokenizer.segment(text);
    }
```

### 1.2.3 索引分词
  * 索引分词IndexTokenizer是面向搜索引擎的分词器，能够对长词全切分
  * 任何分词器都可以通过基类Segment的enableIndexMode方法激活索引模式
  * 优势
    * 适用于长文本分词，能有效分出用户搜索的关键字

```java_holder_method_tree
    /**
     * 索引分词
     * 		索引分词IndexTokenizer是面向搜索引擎的分词器，能够对长词全切分，另外通过term.offset可以获取单词在文本中的偏移量。
     * @param text
     * @return
     */
    public static List<Term> indexTokenizer(String text){
        return IndexTokenizer.segment(text);
    }
```

> 其它分词器与上述几个分词器差不多，所有分词器都在在`com.hankcs.hanlp.tokenizer`包下面，还有繁体分词、url分词等，使用方法大同小异

### 1.2.4 动态词典
  * 动态增删不会持久化到词典文件
  * 词典添加后对所有分词器都有效
  * 词典如果不添加词性，默认为n(名词)
  * 词典≠中文分词，添加到词典中的词不能保证必定分出来
  * 通过设置  Segment.enableCustomDictionaryForcing(true) 可以解决上述问题

```java_holder_method_tree
    /**
     * 添加词典
     * 	动态增删不会持久化到词典文件
     * 	可以在程序启动时把自定义词典通过这个方法添加
     * 	词典对所有分词器都有效
     * 	词典≠中文分词，添加到词典中的词不能保证必定分出来
     * @param strs
     */
    public static void addDictionary(List<String> strs) {
        strs.stream().parallel().forEach(CustomDictionary::add);
    }
	
```

### 1.2.5 关键词提取
  * 从一段话中提取关键词（对类似标题这样的小文本，经测试不太准确）
  * 传入目标文档和所需最大关键句的个数
  * [算法](https://www.hankcs.com/nlp/textrank-algorithm-to-extract-the-keywords-java-implementation.html)

```java_holder_method_tree
    /**
     *
     * @param doc 目标文档
     * @param size     需要的关键句的个数
     * @return 关键句列表
     */
    public static List<String> getKeywordList(String doc, Integer size){
        return TextRankKeyword.getKeywordList(doc, size);
    }
```

### 1.2.6 获取摘要
  * 从一段话中提取摘要（对类似标题这样的小文本，经测试不太准确）
  * 传入源文档和最大摘要数量
  * [算法](https://www.hankcs.com/nlp/textrank-algorithm-java-implementation-of-automatic-abstract.html)

```java_holder_method_tree
    /**
     * 获取摘要
     * @param doc 目标文档
     * @param size     最大摘要数量
     * @return 摘要列表
     */
    public static List<String> extractSummary(String doc, Integer size){
        return TextRankSentence.getTopSentenceList(doc, size);
    }
```

### 1.2.7 获取文本拼音
  * 传入文本，返回```List<Pinyin>```
  * ```Pinyin```对象中的属性
    * 直接输出对象，获取文本的拼音和数字音调
    * ```pinyin.getPinyinWithToneMark()```获取拼音和符号音调
    * ```inyin.getPinyinWithoutTone()```只获取拼音
    * ```pinyin.getTone()```获取声调
    * ```pinyin.getShengmu()```获取声母
    * ```pinyin.getYunmu()```获取韵母
    * ```pinyin.getHead()```获取输入法头

```java_holder_method_tree
    /**
     * 获取文本的拼音
     * @param text
     * @return
     */
    public static List<Pinyin> convertToPinyinList(String text){
        List<Pinyin> pinyinList = PinyinDictionary.convertToPinyin(text);
        return pinyinList;
    }
```

### 1.2.8 文本推荐
  * 传入文本列表和一个词组，返回最能反映词组语境的一个或多个文本
  * 经测试，精确度不太理想

```java_holder_method_tree
    /**
     * 文本推荐
     * 	从所提供的句子列表中，根据传入的key，返回最合适的一句话
     * @param strs 句子列表
     * @param key 
     * @return
     */
    public static List<String> textRecommendation(List<String> strs, String key) {
        Suggester suggester = new Suggester();
        strs.forEach(suggester::addSentence);
        List<String> list = suggester.suggest(key, 1);
        return list;
    }
```
### 1.2.9 文本相似度
  * hanLP提供了文本的相似度匹配，传入需要比较的两个文本，返回一个`float`，越接近于1越相似
  * 需要额外加载模型，通过`new WordVectorModel("data/msr_vectors.txt")`加载模型地址
  * hanLP提供了一个预训练模型：[地址](https://pan.baidu.com/s/1qYFozrY)；
  * 其它模型：[地址](https://github.com/Embedding/Chinese-Word-Vectors)
  * 模型编码必须为UTF-8,格式为txt
  * 使用命令行训练模型：`java -cp hanlp.jar com.hankcs.hanlp.mining.word2vec.Train -input msr_training.utf8 -output msr.txt`

```java_holder_method_tree
    /**
     * hanlp中提供的相识度匹配需要加载一个预训练的模型，
     * 	模型可以在 https://github.com/Embedding/Chinese-Word-Vectors 中下载
     * 	模型很大 无法下载，这里没有做测试
     * 	模型必须为UTF-8
     * @param sentence1
     * @param sentence2
     * @return
     */
    public static float testSimilarity(String sentence1, String sentence2) {
        float f = 0f;
        try {
            DocVectorModel DocVectorModel = new DocVectorModel(new WordVectorModel("data/msr_vectors.txt"));
            f = DocVectorModel.similarity(sentence1,sentence2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f;
    }
```
### 1.2.10 相似度匹配
  * 仿造HanLP的相似度算法，利用默认词典进行分词后的相似度计算
  * 参数和返回值与上者相同
  * 算法都使用 [余弦相似度匹配](http://www.ruanyifeng.com/blog/2013/03/cosine_similarity.html)

```java_holder_method_tree
    /**
     * 模拟hanlp的相似度匹配的逻辑
     * 	在不考虑分词后向量的误差的情况下，与hanlp的相似度应该是一样的
     * 	必须统一转为简体后再匹配
     * 	余弦相识度匹配http://www.ruanyifeng.com/blog/2013/03/cosine_similarity.html
     * @param sentence1
     * @param sentence2
     * @return
     */
    public static double getSimilarity(String sentence1, String sentence2) {
        log.info("{}",getSplitWords(sentence1).toString());
        log.info("{}",getSplitWords(sentence2).toString());
        List<String> sent1Words = getSplitWords(sentence1);
        List<String> sent2Words = getSplitWords(sentence2);
        List<String> allWords = mergeList(sent1Words, sent2Words);

        int[] statistic1 = statistic(allWords, sent1Words);
        int[] statistic2 = statistic(allWords, sent2Words);

        double dividend = 0;
        double divisor1 = 0;
        double divisor2 = 0;
        for (int i = 0; i < statistic1.length; i++) {
            dividend += statistic1[i] * statistic2[i];
            divisor1 += Math.pow(statistic1[i], 2);
            divisor2 += Math.pow(statistic2[i], 2);
        }

        return dividend / (Math.sqrt(divisor1) * Math.sqrt(divisor2));
    }
```
### 1.2.11 语义距离

* 计算两个词语的语义距离
* 与文本相似度相同需要加载训练模型
* 传入需要比较的两个参数，返回`float`，越接近1越相近
```java_holder_method_tree
    /**
     * 计算两个词语的语义距离
     *	需加载训练模型
     * @param what 一个词
     * @param with 另一个词
     * @return 余弦距离
     */
    public static float similarity(String what, String with) throws IOException {
        WordVectorModel wordVectorModel = new WordVectorModel("msr.txt");
        return wordVectorModel.similarity(what, with);
    }
```
### 1.2.12 寻找近义词

* 找出与某个词语最相似的N个词语
* 与文本相似度相同需要加载训练模型
* 传入需要查找的词，键值对列表, 键是相似词语, 值是相似度, 按相似度降序排列
* 只会在现有词库中寻找
```java_holder_method_tree
    /**
     * 找出与某个词语最相似的N个词语
     *	需加载训练模型
     * @param word 一个词
     * @return 键值对列表, 键是相似词语, 值是相似度, 按相似度降序排列
     */
    public static List<Map.Entry<String,Float>> nearest(String word) throws IOException {
        WordVectorModel wordVectorModel = new WordVectorModel("msr.txt");
        return wordVectorModel.nearest(word);
    }
```
### 1.2.13 新词发现
  * 获取文本分词后字典中没有的词
  * 可以作为扩展词汇动态加入词典
  * 文本越大效率越高，建议将多条新闻标题和内容合并后一起处理
```java_holder_method_tree
    /**
     * 内容中的新词发现
     * 	可以作为扩展词汇
     * 	文本越大效率越高，建议将多条新闻标题和内容合并后一起处理
     * @param content
     * @return
     */
    public static List<WordInfo> extractWords(String content){
        NewWordDiscover discover = new NewWordDiscover(4, 0.0f, .5f, 100f, true);
        return discover.discover(content, 20);
    }
```
### 1.2.14 繁体简体转换

* 输入需要转换的文本，和转换类型:ft（转为繁体）、jt（转为简体）
* 默认将繁体转为简体
* 内部使用繁简词典匹配转换
```java_holder_method_tree
    /**
     * 繁体简体转换
     * @param text
     * @param toType
     * @return
     */
    public static String convertChinese(String text,String toType) {
        if (StringUtil.isEmpty(toType)) {
            if (toType.equals("ft")) {
                //转繁体
                return SimplifiedChineseDictionary.convertToTraditionalChinese(text);
            }else if(toType.equals("jt")) {
                //转简体
                return TraditionalChineseDictionary.convertToSimplifiedChinese(text);
            }
        }
        return TraditionalChineseDictionary.convertToSimplifiedChinese(text);
    }
```
### 1.2.15 debug模式
  * 创建对象时可以选择是否开启debug模式，默认为不开启，开启后降低性能
```java_holder_method_tree
    private HanlpUtil(boolean isDebugger) {
        if (isDebugger) {
            HanLP.Config.enableDebug();
        }
    }
```

> 所有的测试用例都存在于`main()`中，取消对应注释可以查看对应方法的调用方式和输出
