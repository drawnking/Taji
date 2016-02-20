package com.zhangqing.taji.emoji;

import com.zhangqing.taji.R;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class EmotionUtils implements Serializable {
	
	/**
	 * key-±íÇéÎÄ×Ö;value-±íÇéÍ¼Æ¬×ÊÔ´
	 */
	public static Map<String, Integer> emojiMap;
	
	static {
		emojiMap = new HashMap<String, Integer>();
		emojiMap.put("[ºÇºÇ]", R.drawable.d_hehe);
		emojiMap.put("[ÎûÎû]", R.drawable.d_xixi);
		emojiMap.put("[¹ş¹ş]", R.drawable.d_haha);
		emojiMap.put("[°®Äã]", R.drawable.d_aini);
		emojiMap.put("[ÍÚ±ÇÊº]", R.drawable.d_wabishi);
		emojiMap.put("[³Ô¾ª]", R.drawable.d_chijing);
		emojiMap.put("[ÔÎ]", R.drawable.d_yun);
		emojiMap.put("[Àá]", R.drawable.d_lei);
		emojiMap.put("[²ö×ì]", R.drawable.d_chanzui);
		emojiMap.put("[×¥¿ñ]", R.drawable.d_zhuakuang);
		emojiMap.put("[ºß]", R.drawable.d_heng);
		emojiMap.put("[¿É°®]", R.drawable.d_keai);
		emojiMap.put("[Å­]", R.drawable.d_nu);
		emojiMap.put("[º¹]", R.drawable.d_han);
		emojiMap.put("[º¦Ğß]", R.drawable.d_haixiu);
		emojiMap.put("[Ë¯¾õ]", R.drawable.d_shuijiao);
		emojiMap.put("[Ç®]", R.drawable.d_qian);
		emojiMap.put("[ÍµĞ¦]", R.drawable.d_touxiao);
		emojiMap.put("[Ğ¦cry]", R.drawable.d_xiaoku);
		emojiMap.put("[doge]", R.drawable.d_doge);
		emojiMap.put("[ß÷ß÷]", R.drawable.d_miao);
		emojiMap.put("[¿á]", R.drawable.d_ku);
		emojiMap.put("[Ë¥]", R.drawable.d_shuai);
		emojiMap.put("[±Õ×ì]", R.drawable.d_bizui);
		emojiMap.put("[±ÉÊÓ]", R.drawable.d_bishi);
		emojiMap.put("[»¨ĞÄ]", R.drawable.d_huaxin);
		emojiMap.put("[¹ÄÕÆ]", R.drawable.d_guzhang);
		emojiMap.put("[±¯ÉË]", R.drawable.d_beishang);
		emojiMap.put("[Ë¼¿¼]", R.drawable.d_sikao);
		emojiMap.put("[Éú²¡]", R.drawable.d_shengbing);
		emojiMap.put("[Ç×Ç×]", R.drawable.d_qinqin);
		emojiMap.put("[Å­Âî]", R.drawable.d_numa);
		emojiMap.put("[Ì«¿ªĞÄ]", R.drawable.d_taikaixin);
		emojiMap.put("[ÀÁµÃÀíÄã]", R.drawable.d_landelini);
		emojiMap.put("[ÓÒºßºß]", R.drawable.d_youhengheng);
		emojiMap.put("[×óºßºß]", R.drawable.d_zuohengheng);
		emojiMap.put("[Ğê]", R.drawable.d_xu);
		emojiMap.put("[Î¯Çü]", R.drawable.d_weiqu);
		emojiMap.put("[ÍÂ]", R.drawable.d_tu);
		emojiMap.put("[¿ÉÁ¯]", R.drawable.d_kelian);
		emojiMap.put("[´ò¹şÆø]", R.drawable.d_dahaqi);
		emojiMap.put("[¼·ÑÛ]", R.drawable.d_jiyan);
		emojiMap.put("[Ê§Íû]", R.drawable.d_shiwang);
		emojiMap.put("[¶¥]", R.drawable.d_ding);
		emojiMap.put("[ÒÉÎÊ]", R.drawable.d_yiwen);
		emojiMap.put("[À§]", R.drawable.d_kun);
		emojiMap.put("[¸ĞÃ°]", R.drawable.d_ganmao);
		emojiMap.put("[°İ°İ]", R.drawable.d_baibai);
		emojiMap.put("[ºÚÏß]", R.drawable.d_heixian);
		emojiMap.put("[ÒõÏÕ]", R.drawable.d_yinxian);
		emojiMap.put("[´òÁ³]", R.drawable.d_dalian);
		emojiMap.put("[ÉµÑÛ]", R.drawable.d_shayan);
		emojiMap.put("[ÖíÍ·]", R.drawable.d_zhutou);
		emojiMap.put("[ĞÜÃ¨]", R.drawable.d_xiongmao);
		emojiMap.put("[ÍÃ×Ó]", R.drawable.d_tuzi);
	}
	
	public static int getImgByName(String imgName) {
		Integer integer = emojiMap.get(imgName);
		return integer == null ? -1 : integer;
	}
}
