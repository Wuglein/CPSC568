/* Generated By:JavaCC: Do not edit this line. ACLParser.java */
package jade.lang.acl;

//#APIDOC_EXCLUDE_FILE


import java.io.*;
import jade.core.AID;

/**
Javadoc documentation for the file
@author Fabio Bellifemine - CSELT S.p.A
@version 0.9
*/

public class ACLParser implements ACLParserConstants {
  ACLMessage msg = new ACLMessage(ACLMessage.NOT_UNDERSTOOD);

  public static void main(String args[]) throws ParseException {
    ACLParser parser = new ACLParser(System.in);

    while (true) {
      try {
        ACLMessage result = parser.Message();
        System.out.println(result);
      }
      catch(ParseException pe) {
        pe.printStackTrace();
        System.exit(1);
      }
    }
  }

  public static ACLParser create() {
    Reader r = new StringReader("");
    return new ACLParser(r);
  }

  public ACLMessage parse(Reader text) throws ParseException {
    ReInit(text);
    return Message();
  }

  public AID parseAID(Reader text) throws ParseException {
    if(text != null) {
      ReInit(text);
    }

    token_source.SwitchTo(AIDSTATE);
    AID result = AgentIdentifier();
    token_source.SwitchTo(DEFAULT);
    return result;
  }

  private String trimQuotes(String s) throws ParseException {
    s = s.trim();
    if(s.startsWith("\"") && (s.endsWith("\"")))
      s = s.substring(1, s.length() - 1);
    return unescape(s);
  }

  private String unescape(String s) throws ParseException {
    StringBuffer result = new StringBuffer(s.length());
    int i;
    for( i=0; i<s.length()-1; i++) {
      if( s.charAt(i) == '\\' && s.charAt(i+1) == '\"' ) {
                        result.append("\"");
                        i++;
                        }
      else {
                                result.append(s.charAt(i));
      }
    }
    // NOTE: if s terminates with \" (this is the case when i == s.length()) the 
    // last character should not be appended as it has already been considered.
    if( i < s.length() ) {
            result.append(s.charAt(s.length()-1));
    }
    return result.toString();
  }

  final public ACLMessage Message() throws ParseException {
   msg.reset();
    jj_consume_token(START);
    MessageType();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SENDER:
      case RECEIVER:
      case CONTENT:
      case REPLY_WITH:
      case REPLY_BY:
      case IN_REPLY_TO:
      case REPLY_TO:
      case ENCODING:
      case LANGUAGE:
      case ONTOLOGY:
      case PROTOCOL:
      case CONVERSATION_ID:
      case USERDEFINEDPARAM:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      MessageParameter();
    }
    jj_consume_token(END);
   {if (true) return msg;}
    throw new Error("Missing return statement in function");
  }

  final public void MessageType() throws ParseException {
  Token t;
    t = jj_consume_token(MESSAGETYPE);
                          msg.setPerformative(ACLMessage.getInteger(t.image));
  }

  final public void MessageParameter() throws ParseException {
  String s; Token t; AID aid;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SENDER:
      jj_consume_token(SENDER);
      aid = AgentIdentifier();
                                                                  msg.setSender(aid); token_source.SwitchTo(MESSAGEPARAMETERSTATE);
      break;
    case RECEIVER:
      jj_consume_token(RECEIVER);
                     msg.clearAllReceiver();
      jj_consume_token(LBRACE2);
      jj_consume_token(SET);
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LBRACE2:
          ;
          break;
        default:
          jj_la1[1] = jj_gen;
          break label_2;
        }
        aid = AgentIdentifier();
                                                msg.addReceiver(aid);
      }
      jj_consume_token(RBRACE2);
          token_source.SwitchTo(MESSAGEPARAMETERSTATE);
      break;
    case CONTENT:
      jj_consume_token(CONTENT);
      s = Content();
                                                  msg.setContent(s); token_source.SwitchTo(MESSAGEPARAMETERSTATE);
      break;
    case REPLY_WITH:
      jj_consume_token(REPLY_WITH);
      s = Expression();
                                                          msg.setReplyWith(s); token_source.SwitchTo(MESSAGEPARAMETERSTATE);
      break;
    case REPLY_BY:
      jj_consume_token(REPLY_BY);
      s = DateTimeToken();
                                                  try { msg.setReplyByDate(ISO8601.toDate(s));} catch (Exception e) {} token_source.SwitchTo(MESSAGEPARAMETERSTATE);
      break;
    case IN_REPLY_TO:
      jj_consume_token(IN_REPLY_TO);
      s = Expression();
                                                          msg.setInReplyTo(s); token_source.SwitchTo(MESSAGEPARAMETERSTATE);
      break;
    case REPLY_TO:
      jj_consume_token(REPLY_TO);
                    msg.clearAllReplyTo();
      jj_consume_token(LBRACE2);
      jj_consume_token(SET);
      label_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LBRACE2:
          ;
          break;
        default:
          jj_la1[2] = jj_gen;
          break label_3;
        }
        aid = AgentIdentifier();
                                              msg.addReplyTo(aid);
      }
      jj_consume_token(RBRACE2);
        token_source.SwitchTo(MESSAGEPARAMETERSTATE);
      break;
    case ENCODING:
      jj_consume_token(ENCODING);
      s = Expression();
                                                  msg.setEncoding(s); token_source.SwitchTo(MESSAGEPARAMETERSTATE);
      break;
    case LANGUAGE:
      jj_consume_token(LANGUAGE);
      s = Expression();
                                                          msg.setLanguage(s); token_source.SwitchTo(MESSAGEPARAMETERSTATE);
      break;
    case ONTOLOGY:
      jj_consume_token(ONTOLOGY);
      s = Expression();
                                                          msg.setOntology(s); token_source.SwitchTo(MESSAGEPARAMETERSTATE);
      break;
    case PROTOCOL:
      jj_consume_token(PROTOCOL);
      s = Word();
                                                          msg.setProtocol(s); token_source.SwitchTo(MESSAGEPARAMETERSTATE);
      break;
    case CONVERSATION_ID:
      jj_consume_token(CONVERSATION_ID);
      s = Expression();
                                                          msg.setConversationId(s); token_source.SwitchTo(MESSAGEPARAMETERSTATE);
      break;
    case USERDEFINEDPARAM:
      t = jj_consume_token(USERDEFINEDPARAM);
      s = Expression();
                                                          msg.addUserDefinedParameter(t.image.substring(3),s); token_source.SwitchTo(MESSAGEPARAMETERSTATE);
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public String Content() throws ParseException {
  String s;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case STRINGLITERAL:
    case PREFIXBYTELENGTHENCODEDSTRING:
      s = Stringa();
                  {if (true) return s;}
      break;
    case WORD:
      s = Word();
                  {if (true) return s;}
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public AID AgentIdentifier() throws ParseException {
 Token t; String s; AID aid;
 AID cur = new AID();
    jj_consume_token(LBRACE2);
    jj_consume_token(AID);
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NAME:
        jj_consume_token(NAME);
        s = Content();
                          cur.setName(s); token_source.SwitchTo(AIDSTATE);
        break;
      case ADDRESSES:
        jj_consume_token(ADDRESSES);
        jj_consume_token(LBRACE2);
        jj_consume_token(SEQUENCE);
                                       token_source.SwitchTo(CONTENTSTATE);
        label_5:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case WORD:
            ;
            break;
          default:
            jj_la1[5] = jj_gen;
            break label_5;
          }
          s = Word();
                  cur.addAddresses(s);
        }
        jj_consume_token(RBRACE);
        token_source.SwitchTo(AIDSTATE);
        break;
      case RESOLVERS:
        jj_consume_token(RESOLVERS);
        jj_consume_token(LBRACE2);
        jj_consume_token(SEQUENCE);
        label_6:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case LBRACE2:
            ;
            break;
          default:
            jj_la1[6] = jj_gen;
            break label_6;
          }
          aid = AgentIdentifier();
                               cur.addResolvers(aid);
        }
        jj_consume_token(RBRACE2);
        token_source.SwitchTo(AIDSTATE);
        break;
      case USERDEFINEDSLOT:
        t = jj_consume_token(USERDEFINEDSLOT);
        s = Expression();
        cur.addUserDefinedSlot(t.image.substring(3),s); token_source.SwitchTo(AIDSTATE);
        break;
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NAME:
      case ADDRESSES:
      case RESOLVERS:
      case USERDEFINEDSLOT:
        ;
        break;
      default:
        jj_la1[8] = jj_gen;
        break label_4;
      }
    }
    jj_consume_token(RBRACE2);
 {if (true) return cur;}
    throw new Error("Missing return statement in function");
  }

  final public String Expression() throws ParseException {
  String s; String s1=new String();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case WORD:
      s = Word();
                                                         {if (true) return s;}
      break;
    case STRINGLITERAL:
    case PREFIXBYTELENGTHENCODEDSTRING:
      s = Stringa();
                                                 {if (true) return s;}
      break;
    case DIGIT:
    case INTEGER:
    case FLOATONE:
    case FLOATTWO:
      s = Number();
                                                 {if (true) return s;}
      break;
    case DATETIME:
      s = DateTimeToken();
                         {if (true) return s;}
      break;
    case LBRACE:
      jj_consume_token(LBRACE);
      label_7:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case DATETIME:
        case WORD:
        case STRINGLITERAL:
        case DIGIT:
        case INTEGER:
        case FLOATONE:
        case FLOATTWO:
        case PREFIXBYTELENGTHENCODEDSTRING:
        case LBRACE:
          ;
          break;
        default:
          jj_la1[9] = jj_gen;
          break label_7;
        }
        s = Expression();
                             s1+=(s+" ");
      }
      jj_consume_token(RBRACE);
                                                                 {if (true) return "("+s1+")";}
      break;
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String Word() throws ParseException {
  Token t;
    t = jj_consume_token(WORD);
                  {if (true) return trimQuotes(t.image);}
    throw new Error("Missing return statement in function");
  }

  final public String Stringa() throws ParseException {
  String s;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case STRINGLITERAL:
      s = StringLiteral();
                                         {if (true) return s;}
      break;
    case PREFIXBYTELENGTHENCODEDSTRING:
      s = ByteLengthEncodedString();
                                 {if (true) return s;}
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String StringLiteral() throws ParseException {
  Token t;
    t = jj_consume_token(STRINGLITERAL);
                                         {if (true) return trimQuotes(t.image);}
    throw new Error("Missing return statement in function");
  }

  final public String ByteLengthEncodedString() throws ParseException {
  Token t;
    t = jj_consume_token(PREFIXBYTELENGTHENCODEDSTRING);
                                      {if (true) return t.image;}
    throw new Error("Missing return statement in function");
  }

  final public String Number() throws ParseException {
  String s;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DIGIT:
      s = Digit();
                                 {if (true) return s;}
      break;
    case INTEGER:
      s = Integer();
                                 {if (true) return s;}
      break;
    case FLOATONE:
    case FLOATTWO:
      s = Float();
                                 {if (true) return s;}
      break;
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String DateTimeToken() throws ParseException {
  Token t; String s;
    t = jj_consume_token(DATETIME);
                                         {if (true) return t.image;}
    throw new Error("Missing return statement in function");
  }

  final public String Digit() throws ParseException {
  Token t;
    t = jj_consume_token(DIGIT);
                                         {if (true) return t.image;}
    throw new Error("Missing return statement in function");
  }

  final public String Integer() throws ParseException {
  Token t; String s=new String();
    // (t="+" {s+=t.image;} | t="-" {s+=t.image;})? ( t=<DIGIT> {s+=t.image;} )+	{return s;}*/
      t = jj_consume_token(INTEGER);
                 {if (true) return t.image;}
    throw new Error("Missing return statement in function");
  }

  final public String Float() throws ParseException {
  Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FLOATONE:
      t = jj_consume_token(FLOATONE);
                                 {if (true) return t.image;}
      break;
    case FLOATTWO:
      t = jj_consume_token(FLOATTWO);
                                 {if (true) return t.image;}
      break;
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  public ACLParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  public Token token, jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[14];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_0();
      jj_la1_1();
   }
   private static void jj_la1_0() {
      jj_la1_0 = new int[] {0xfff8000,0x0,0x0,0xfff8000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,};
   }
   private static void jj_la1_1() {
      jj_la1_1 = new int[] {0x0,0x800000,0x800000,0x0,0x10c,0x4,0x800000,0x3c0000,0x3c0000,0x5fe,0x5fe,0x108,0xf0,0xc0,};
   }

  public ACLParser(java.io.InputStream stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ACLParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  public void ReInit(java.io.InputStream stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  public ACLParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ACLParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  public ACLParser(ACLParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  public void ReInit(ACLParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  final private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  final private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.Vector jj_expentries = new java.util.Vector();
  private int[] jj_expentry;
  private int jj_kind = -1;

  public ParseException generateParseException() {
    jj_expentries.removeAllElements();
    boolean[] la1tokens = new boolean[56];
    for (int i = 0; i < 56; i++) {
      la1tokens[i] = false;
    }
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 14; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 56; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.addElement(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.elementAt(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  final public void enable_tracing() {
  }

  final public void disable_tracing() {
  }

}
