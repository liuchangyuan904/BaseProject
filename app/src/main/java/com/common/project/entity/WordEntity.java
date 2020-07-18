package com.common.project.entity;

import java.util.List;

public class WordEntity {

    /**
     * wordRank : 1
     * headWord : hello
     * content : {"word":{"wordHead":"hello","wordId":"WaiYanSheChuZhong_1_1","content":{"sentence":{"sentences":[{"sContent":"Hello, John! How are you?","sCn":"喂，约翰！你好吗？"},{"sContent":"Stanley, come and say hello to your nephew.","sCn":"斯坦利，过来和你的侄子打声招呼。"},{"sContent":"Well, hello there ! I haven\u2019t seen you for ages.","sCn":"嗨，你好！好久不见了。"}],"desc":"例句"},"usphone":"helˈō","syno":{"synos":[{"pos":"int","tran":"喂；哈罗","hwds":[{"w":"hallo"},{"w":"holloo"}]}],"desc":"同近"},"ukphone":"həˈləʊ","ukspeech":"hello&type=1","phrase":{"phrases":[{"pContent":"say hello","pCn":"打招呼；问好"},{"pContent":"hello everyone","pCn":"大家好"},{"pContent":"hello and welcome","pCn":"欢迎莅临"},{"pContent":"hello again","pCn":"回魂妻（电影名称）"}],"desc":"短语"},"usspeech":"hello&type=2","trans":[{"tranCn":"你好，喂","descOther":"英释","descCn":"中释","pos":"int","tranOther":"used as a greeting when you see or meet someone"}]}}}
     * bookId : WaiYanSheChuZhong_1
     */

    private int wordRank;
    private String headWord;
    private ContentBeanX content;
    private String bookId;

    public int getWordRank() {
        return wordRank;
    }

    public void setWordRank(int wordRank) {
        this.wordRank = wordRank;
    }

    public String getHeadWord() {
        return headWord;
    }

    public void setHeadWord(String headWord) {
        this.headWord = headWord;
    }

    public ContentBeanX getContent() {
        return content;
    }

    public void setContent(ContentBeanX content) {
        this.content = content;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public static class ContentBeanX {
        /**
         * word : {"wordHead":"hello","wordId":"WaiYanSheChuZhong_1_1","content":{"sentence":{"sentences":[{"sContent":"Hello, John! How are you?","sCn":"喂，约翰！你好吗？"},{"sContent":"Stanley, come and say hello to your nephew.","sCn":"斯坦利，过来和你的侄子打声招呼。"},{"sContent":"Well, hello there ! I haven\u2019t seen you for ages.","sCn":"嗨，你好！好久不见了。"}],"desc":"例句"},"usphone":"helˈō","syno":{"synos":[{"pos":"int","tran":"喂；哈罗","hwds":[{"w":"hallo"},{"w":"holloo"}]}],"desc":"同近"},"ukphone":"həˈləʊ","ukspeech":"hello&type=1","phrase":{"phrases":[{"pContent":"say hello","pCn":"打招呼；问好"},{"pContent":"hello everyone","pCn":"大家好"},{"pContent":"hello and welcome","pCn":"欢迎莅临"},{"pContent":"hello again","pCn":"回魂妻（电影名称）"}],"desc":"短语"},"usspeech":"hello&type=2","trans":[{"tranCn":"你好，喂","descOther":"英释","descCn":"中释","pos":"int","tranOther":"used as a greeting when you see or meet someone"}]}}
         */

        private WordBean word;

        public WordBean getWord() {
            return word;
        }

        public void setWord(WordBean word) {
            this.word = word;
        }

        public static class WordBean {
            /**
             * wordHead : hello
             * wordId : WaiYanSheChuZhong_1_1
             * content : {"sentence":{"sentences":[{"sContent":"Hello, John! How are you?","sCn":"喂，约翰！你好吗？"},{"sContent":"Stanley, come and say hello to your nephew.","sCn":"斯坦利，过来和你的侄子打声招呼。"},{"sContent":"Well, hello there ! I haven\u2019t seen you for ages.","sCn":"嗨，你好！好久不见了。"}],"desc":"例句"},"usphone":"helˈō","syno":{"synos":[{"pos":"int","tran":"喂；哈罗","hwds":[{"w":"hallo"},{"w":"holloo"}]}],"desc":"同近"},"ukphone":"həˈləʊ","ukspeech":"hello&type=1","phrase":{"phrases":[{"pContent":"say hello","pCn":"打招呼；问好"},{"pContent":"hello everyone","pCn":"大家好"},{"pContent":"hello and welcome","pCn":"欢迎莅临"},{"pContent":"hello again","pCn":"回魂妻（电影名称）"}],"desc":"短语"},"usspeech":"hello&type=2","trans":[{"tranCn":"你好，喂","descOther":"英释","descCn":"中释","pos":"int","tranOther":"used as a greeting when you see or meet someone"}]}
             */

            private String wordHead;
            private String wordId;
            private ContentBean content;

            public String getWordHead() {
                return wordHead;
            }

            public void setWordHead(String wordHead) {
                this.wordHead = wordHead;
            }

            public String getWordId() {
                return wordId;
            }

            public void setWordId(String wordId) {
                this.wordId = wordId;
            }

            public ContentBean getContent() {
                return content;
            }

            public void setContent(ContentBean content) {
                this.content = content;
            }

            public static class ContentBean {
                /**
                 * sentence : {"sentences":[{"sContent":"Hello, John! How are you?","sCn":"喂，约翰！你好吗？"},{"sContent":"Stanley, come and say hello to your nephew.","sCn":"斯坦利，过来和你的侄子打声招呼。"},{"sContent":"Well, hello there ! I haven\u2019t seen you for ages.","sCn":"嗨，你好！好久不见了。"}],"desc":"例句"}
                 * usphone : helˈō
                 * syno : {"synos":[{"pos":"int","tran":"喂；哈罗","hwds":[{"w":"hallo"},{"w":"holloo"}]}],"desc":"同近"}
                 * ukphone : həˈləʊ
                 * ukspeech : hello&type=1
                 * phrase : {"phrases":[{"pContent":"say hello","pCn":"打招呼；问好"},{"pContent":"hello everyone","pCn":"大家好"},{"pContent":"hello and welcome","pCn":"欢迎莅临"},{"pContent":"hello again","pCn":"回魂妻（电影名称）"}],"desc":"短语"}
                 * usspeech : hello&type=2
                 * trans : [{"tranCn":"你好，喂","descOther":"英释","descCn":"中释","pos":"int","tranOther":"used as a greeting when you see or meet someone"}]
                 */

                private SentenceBean sentence;
                private String usphone;
                private SynoBean syno;
                private String ukphone;
                private String ukspeech;
                private PhraseBean phrase;
                private String usspeech;
                private List<TransBean> trans;

                public SentenceBean getSentence() {
                    return sentence;
                }

                public void setSentence(SentenceBean sentence) {
                    this.sentence = sentence;
                }

                public String getUsphone() {
                    return usphone;
                }

                public void setUsphone(String usphone) {
                    this.usphone = usphone;
                }

                public SynoBean getSyno() {
                    return syno;
                }

                public void setSyno(SynoBean syno) {
                    this.syno = syno;
                }

                public String getUkphone() {
                    return ukphone;
                }

                public void setUkphone(String ukphone) {
                    this.ukphone = ukphone;
                }

                public String getUkspeech() {
                    return ukspeech;
                }

                public void setUkspeech(String ukspeech) {
                    this.ukspeech = ukspeech;
                }

                public PhraseBean getPhrase() {
                    return phrase;
                }

                public void setPhrase(PhraseBean phrase) {
                    this.phrase = phrase;
                }

                public String getUsspeech() {
                    return usspeech;
                }

                public void setUsspeech(String usspeech) {
                    this.usspeech = usspeech;
                }

                public List<TransBean> getTrans() {
                    return trans;
                }

                public void setTrans(List<TransBean> trans) {
                    this.trans = trans;
                }

                public static class SentenceBean {
                    /**
                     * sentences : [{"sContent":"Hello, John! How are you?","sCn":"喂，约翰！你好吗？"},{"sContent":"Stanley, come and say hello to your nephew.","sCn":"斯坦利，过来和你的侄子打声招呼。"},{"sContent":"Well, hello there ! I haven\u2019t seen you for ages.","sCn":"嗨，你好！好久不见了。"}]
                     * desc : 例句
                     */

                    private String desc;
                    private List<SentencesBean> sentences;

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }

                    public List<SentencesBean> getSentences() {
                        return sentences;
                    }

                    public void setSentences(List<SentencesBean> sentences) {
                        this.sentences = sentences;
                    }

                    public static class SentencesBean {
                        /**
                         * sContent : Hello, John! How are you?
                         * sCn : 喂，约翰！你好吗？
                         */

                        private String sContent;
                        private String sCn;

                        public String getSContent() {
                            return sContent;
                        }

                        public void setSContent(String sContent) {
                            this.sContent = sContent;
                        }

                        public String getSCn() {
                            return sCn;
                        }

                        public void setSCn(String sCn) {
                            this.sCn = sCn;
                        }
                    }
                }

                public static class SynoBean {
                    /**
                     * synos : [{"pos":"int","tran":"喂；哈罗","hwds":[{"w":"hallo"},{"w":"holloo"}]}]
                     * desc : 同近
                     */

                    private String desc;
                    private List<SynosBean> synos;

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }

                    public List<SynosBean> getSynos() {
                        return synos;
                    }

                    public void setSynos(List<SynosBean> synos) {
                        this.synos = synos;
                    }

                    public static class SynosBean {
                        /**
                         * pos : int
                         * tran : 喂；哈罗
                         * hwds : [{"w":"hallo"},{"w":"holloo"}]
                         */

                        private String pos;
                        private String tran;
                        private List<HwdsBean> hwds;

                        public String getPos() {
                            return pos;
                        }

                        public void setPos(String pos) {
                            this.pos = pos;
                        }

                        public String getTran() {
                            return tran;
                        }

                        public void setTran(String tran) {
                            this.tran = tran;
                        }

                        public List<HwdsBean> getHwds() {
                            return hwds;
                        }

                        public void setHwds(List<HwdsBean> hwds) {
                            this.hwds = hwds;
                        }

                        public static class HwdsBean {
                            /**
                             * w : hallo
                             */

                            private String w;

                            public String getW() {
                                return w;
                            }

                            public void setW(String w) {
                                this.w = w;
                            }
                        }
                    }
                }

                public static class PhraseBean {
                    /**
                     * phrases : [{"pContent":"say hello","pCn":"打招呼；问好"},{"pContent":"hello everyone","pCn":"大家好"},{"pContent":"hello and welcome","pCn":"欢迎莅临"},{"pContent":"hello again","pCn":"回魂妻（电影名称）"}]
                     * desc : 短语
                     */

                    private String desc;
                    private List<PhrasesBean> phrases;

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }

                    public List<PhrasesBean> getPhrases() {
                        return phrases;
                    }

                    public void setPhrases(List<PhrasesBean> phrases) {
                        this.phrases = phrases;
                    }

                    public static class PhrasesBean {
                        /**
                         * pContent : say hello
                         * pCn : 打招呼；问好
                         */

                        private String pContent;
                        private String pCn;

                        public String getPContent() {
                            return pContent;
                        }

                        public void setPContent(String pContent) {
                            this.pContent = pContent;
                        }

                        public String getPCn() {
                            return pCn;
                        }

                        public void setPCn(String pCn) {
                            this.pCn = pCn;
                        }
                    }
                }

                public static class TransBean {
                    /**
                     * tranCn : 你好，喂
                     * descOther : 英释
                     * descCn : 中释
                     * pos : int
                     * tranOther : used as a greeting when you see or meet someone
                     */

                    private String tranCn;
                    private String descOther;
                    private String descCn;
                    private String pos;
                    private String tranOther;

                    public String getTranCn() {
                        return tranCn;
                    }

                    public void setTranCn(String tranCn) {
                        this.tranCn = tranCn;
                    }

                    public String getDescOther() {
                        return descOther;
                    }

                    public void setDescOther(String descOther) {
                        this.descOther = descOther;
                    }

                    public String getDescCn() {
                        return descCn;
                    }

                    public void setDescCn(String descCn) {
                        this.descCn = descCn;
                    }

                    public String getPos() {
                        return pos;
                    }

                    public void setPos(String pos) {
                        this.pos = pos;
                    }

                    public String getTranOther() {
                        return tranOther;
                    }

                    public void setTranOther(String tranOther) {
                        this.tranOther = tranOther;
                    }
                }
            }
        }
    }
}
