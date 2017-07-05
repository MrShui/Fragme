package com.iuicity.xinjr.network.api;

import com.tiannt.indescribable.network.bean.ResultModel;
import com.tiannt.indescribable.network.bean.req.PersonalDataResp;
import com.tiannt.indescribable.network.bean.resp.AndroidRechargeResp;
import com.tiannt.indescribable.network.bean.resp.AttentionResp;
import com.tiannt.indescribable.network.bean.resp.DetailsResp;
import com.tiannt.indescribable.network.bean.resp.DynamicResp;
import com.tiannt.indescribable.network.bean.resp.HomeHotResp;
import com.tiannt.indescribable.network.bean.resp.HotInHomeResp;
import com.tiannt.indescribable.network.bean.resp.HotSearchResp;
import com.tiannt.indescribable.network.bean.resp.InvitFrientListResp;
import com.tiannt.indescribable.network.bean.resp.InviteListResp;
import com.tiannt.indescribable.network.bean.resp.IsTopResp;
import com.tiannt.indescribable.network.bean.resp.LoginResp;
import com.tiannt.indescribable.network.bean.resp.MessageNumberResp;
import com.tiannt.indescribable.network.bean.resp.MineEarningsResp;
import com.tiannt.indescribable.network.bean.resp.MyConcernedResp;
import com.tiannt.indescribable.network.bean.resp.MyPurchaseResp;
import com.tiannt.indescribable.network.bean.resp.NewstResp;
import com.tiannt.indescribable.network.bean.resp.PaySelectResp;
import com.tiannt.indescribable.network.bean.resp.PaySelfResult;
import com.tiannt.indescribable.network.bean.resp.PersonageResp;
import com.tiannt.indescribable.network.bean.resp.PersonalDataDetailResp;
import com.tiannt.indescribable.network.bean.resp.PriceResp;
import com.tiannt.indescribable.network.bean.resp.QueryOrderStateResp;
import com.tiannt.indescribable.network.bean.resp.RechargeAmtListResp;
import com.tiannt.indescribable.network.bean.resp.RechargeRecordResp;
import com.tiannt.indescribable.network.bean.resp.RegisterResp;
import com.tiannt.indescribable.network.bean.resp.ReportResp;
import com.tiannt.indescribable.network.bean.resp.SearchDynamicResp;
import com.tiannt.indescribable.network.bean.resp.SearchRecommnedListResp;
import com.tiannt.indescribable.network.bean.resp.SearchResultResp;
import com.tiannt.indescribable.network.bean.resp.SellRecordResp;
import com.tiannt.indescribable.network.bean.resp.SystemMessageResp;
import com.tiannt.indescribable.network.bean.resp.TradeRecordResp;
import com.tiannt.indescribable.network.bean.resp.UploadPictureResp;
import com.tiannt.indescribable.network.bean.resp.WechatInfoResp;
import com.tiannt.indescribable.network.bean.resp.WithdrawResp;
import com.tiannt.indescribable.network.bean.resp.WxPayResp;
import com.tiannt.indescribable.network.bean.resp.YiLianCalBackResp;
import com.tiannt.indescribable.network.bean.resp.YiLianPayResp;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * 真实接口请求
 * Created by Shui on 16/10/28.
 */

public interface Api {
    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("api/Loading/sendKey")
    Observable<ResultModel<String>> getPubkey(@Field("equipment") String equipment);

    /**
     * 获取验证码
     */
    @FormUrlEncoded
    @POST("api/sms/sendCode")
    Observable<ResultModel<String>> getCode(@Field("mobile") String mobile, @Field("action") String action);

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("api/register/registerStepOne")
    Observable<ResultModel<RegisterResp>> getRegister(@Field("equipment") String equipment, @Field("encrypt") String encrypt,
                                                      @Field("code") String code);

    /**
     * 注册第二步
     *
     * @return
     */

    @POST("api/register/registerStepTwoV1_2")
    @Multipart
    Observable<ResultModel<LoginResp>> getRegisterStepTwo(@Part List<MultipartBody.Part> partList);

    /**
     * 登录
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/login/loginV1_2")
    Observable<ResultModel<LoginResp>> login(@Field("equipment") String equipment, @Field("encrypt") String encrypt,
                                             @Field("version") String version, @Field("platform") String platform,
                                             @Field("from") String from);

    /**
     * 忘记密码
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/login/forgetPassword")
    Observable<ResultModel<String>> forgetPassword(@Field("equipment") String equipment, @Field("encrypt") String encrypt,
                                                   @Field("code") String code);

    /**
     * 系统通知
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/notice/noticeList")
    Observable<ResultModel<List<SystemMessageResp>>> getSystemMessage(@Field("encrypt") String encrypt, @Field("page") String page);

    /**
     * 个人消息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/message/messageList")
    Observable<ResultModel<List<PersonageResp>>> getPersonage(@Field("encrypt") String encrypt, @Field("page") String page);

    /**
     * 通知详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/notice/noticeShow")
    Observable<ResultModel<DetailsResp>> noticeShow(@Field("encrypt") String encrypt, @Field("id") String id);

    /**
     * 获取首页热门数据
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/index/index")
    Observable<ResultModel<HomeHotResp>> getHomeHotData(@Field("encrypt") String encrypt, @Field("page") String page);

    /**
     * 我的界面
     *
     * @param encrypt
     * @return
     */
    @FormUrlEncoded
    @POST("api/Ucenter/personal")
    Observable<ResultModel<PersonalDataResp>> getPersonalData(@Field("encrypt") String encrypt);

    /**
     * 个人资料详情
     *
     * @param encrypt
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("api/Ucenter/uCenter")
    Observable<ResultModel<PersonalDataDetailResp>> getPersonDataDetail(@Field("encrypt") String encrypt,
                                                                        @Field("id") String id, @Field("page") String page);

    /**
     * 检测token有效性
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/loading/checkToken")
    Observable<ResultModel<Object>> checkToken(@Field("encrypt") String encrypt);

    /**
     * 退出登录
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/login/logout")
    Observable<ResultModel<Object>> logout(@Field("encrypt") String encrypt);

    /**
     * 动态列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/dynamic/dynamicList")
    Observable<ResultModel<List<DynamicResp>>> getDynamic(@Field("encrypt") String encrypt, @Field("page") String page);

    /**
     * 首页最新模特列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/index/newModelV1_2")
    Observable<ResultModel<NewstResp>> newModel(@Field("encrypt") String encrypt, @Field("page") String page);

    /**
     * 关注列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/follow/followDynamicList")
    Observable<ResultModel<List<DynamicResp>>> followList(@Field("encrypt") String encrypt, @Field("page") String page);

    /**
     * 个人资料修改
     *
     * @param partList
     * @return
     */
    @Multipart
    @POST("api/Ucenter/personalSaveV1_2")
    Observable<ResultModel<Object>> keepPersonalData(@Part List<MultipartBody.Part> partList);


    /**
     * 动态添加
     *
     * @return
     */
    @Multipart
    @POST("api/dynamic/dynamicAdd")
    Observable<ResultModel<Object>> dynamicAdd(@Part List<MultipartBody.Part> partList);

    /**
     * 动态添加
     *
     * @return
     */
    @POST("api/dynamic/dynamicTag")
    Observable<ResultModel<List<String>>> dynamicTag();

    /**
     * 出售微信价格
     *
     * @return
     */
    @POST("api/wechat/wechatPrice")
    Observable<ResultModel<PriceResp>> price();

    /**
     * 微信号出售
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/wechat/wechatSell")
    Observable<ResultModel<String>> wechatSell(@Field("encrypt") String encrypt);

    /**
     * 微信号下架
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/wechat/wechatOffShel")
    Observable<ResultModel<String>> wechatOffShel(@Field("encrypt") String encrypt);

    /**
     * 微信出售信息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/wechat/wechatInfo")
    Observable<ResultModel<WechatInfoResp>> wechatInfo(@Field("encrypt") String encrypt);

    /**
     * 微信出售记录
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/wechat/wechatRecord")
    Observable<ResultModel<List<SellRecordResp>>> wechatRecord(@Field("encrypt") String encrypt, @Field("page") String page);

    /**
     * 热门搜索
     *
     * @return
     */
    @POST("api/search/searchRecommend")
    Observable<ResultModel<HotSearchResp>> HotSearchData();

    /**
     * 搜索结果
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/search/search")
    Observable<ResultModel<SearchResultResp>> getSearchData(@Field("encrypt") String encrypt, @Field("search") String search, @Field("page") String page);


    /**
     * 添加关注
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/follow/follow")
    Observable<ResultModel<AttentionResp>> addAttention(@Field("encrypt") String encrypt, @Field("uid") String uid);

    /**
     * 我的关注列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/follow/followList")
    Observable<ResultModel<List<MyConcernedResp>>> getConcernedData(@Field("encrypt") String encrypt, @Field("page") String page);

    /**
     * 我的粉丝列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/follow/fansList")
    Observable<ResultModel<List<MyConcernedResp>>> getFansData(@Field("encrypt") String encrypt, @Field("page") String page);

    /**
     * 建议与反馈
     *
     * @param encrypt
     * @param content
     * @return
     */
    @FormUrlEncoded
    @POST("api/feedBack/feedBack")
    Observable<ResultModel<Object>> sendFeedback(@Field("encrypt") String encrypt, @Field("content") String content);

    /**
     * 我的收益
     *
     * @param encrypt
     * @return
     */
    @FormUrlEncoded
    @POST("api/account/account")
    Observable<ResultModel<MineEarningsResp>> MineEarningsData(@Field("encrypt") String encrypt);


    /**
     * 动态添加
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/dynamic/dynamicAgree")
    Observable<ResultModel<String>> dynamicAgree(@Field("encrypt") String encrypt, @Field("id") String id);

    /**
     * 我购买的
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/order/orderList")
    Observable<ResultModel<List<MyPurchaseResp>>> orderList(@Field("encrypt") String encrypt, @Field("page") String page);

    /**
     * 确认好友
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/order/makeFriend")
    Observable<ResultModel<String>> makeFriend(@Field("encrypt") String encrypt);

    /**
     * 举报类型列表
     *
     * @return
     */
    @GET("api/report/reportType")
    Observable<ResultModel<List<ReportResp>>> reportType();


    /**
     * 邀请列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/invite/inviteList")
    Observable<ResultModel<InviteListResp>> inviteList(@Field("encrypt") String encrypt, @Field("page") String page);

    /**
     * 举报类型列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/report/report")
    Observable<ResultModel<String>> report(@Field("encrypt") String encrypt, @Field("id") String id,
                                           @Field("type_id") String type_id);

    /**
     * 动态置顶
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/dynamic/dynamicTop")
    Observable<ResultModel<IsTopResp>> dynamicTop(@Field("encrypt") String encrypt, @Field("id") String id);

    /**
     * 动态删除
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/dynamic/dynamicDel")
    Observable<ResultModel<String>> dynamicDel(@Field("encrypt") String encrypt, @Field("did") String id);

    /**
     * 关注
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/follow/follow")
    Observable<ResultModel<AttentionResp>> follow(@Field("encrypt") String encrypt, @Field("uid") String uid);

    /**
     * 支付宝订单状态查询
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/Alipay/payComplete")
    Observable<ResultModel<QueryOrderStateResp>> queryOrderState(@Field("encrypt") String encrypt);

    /**
     * 饭粒购买动态（发红包查看图片）
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/order/MiliPay")
    Observable<ResultModel<Object>> MiliPay(@Field("encrypt") String encrypt, @Field("wechat") String wechat);


//    /**
//     * 查看微信号
//     *
//     * @param encrypt
//     * @param
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("api/wechat/wechatShow")
//    Observable<ResultModel<WxNubResp>> checkWxNub(@Field("encrypt") String encrypt);


    /**
     * 添加银行卡
     *
     * @param encrypt
     * @param name
     * @param mobile
     * @param bank_name
     * @param bank_address
     * @param bank_branch
     * @return
     */
    @FormUrlEncoded
    @POST("api/bank/bankAdd")
    Observable<ResultModel<String>> keepBankCard(@Field("encrypt") String encrypt, @Field("equipment") String equipment, @Field("name") String name, @Field("mobile") String mobile, @Field("bank_name") String bank_name, @Field("bank_address") String bank_address, @Field("bank_branch") String bank_branch);


    /**
     * 提现页面银行卡信息
     *
     * @param encrypt
     * @return
     */
    @FormUrlEncoded
    @POST("api/withdrawals/withdrawalsTo")
    Observable<ResultModel<WithdrawResp>> getWithdrawData(@Field("encrypt") String encrypt);

//暂时不做
//    /**
//     * 银行卡信息（修改银行卡信息时获取）
//     * @param encrypt
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("api/bank/bankInfo")
//    Observable<ResultModel<BankCardDataResp>> getBankCardData(@Field("encrypt") String encrypt);

    /**
     * 保存修改银行卡信息
     *
     * @param encrypt
     * @param name
     * @param mobile
     * @param bank_name
     * @param bank_address
     * @param bank_branch
     * @return
     */
    @FormUrlEncoded
    @POST("api/bank/bankSave")
    Observable<ResultModel<Object>> changeBankCardData(@Field("encrypt") String encrypt, @Field("name") String name, @Field("mobile") String mobile, @Field("bank_name") String bank_name, @Field("bank_address") String bank_address, @Field("bank_branch") String bank_branch);

    /**
     * 提现申请
     *
     * @param encrypt
     * @return
     */
    @FormUrlEncoded
    @POST("api/withdrawals/withdrawals")
    Observable<ResultModel<Object>> withDrawApply(@Field("encrypt") String encrypt);

    /**
     * 交易记录
     *
     * @param encrypt
     * @param page
     * @return
     */
    @FormUrlEncoded
    @POST("api/order/orderRecord")
    Observable<ResultModel<List<TradeRecordResp>>> getTradeRecordData(@Field("encrypt") String encrypt, @Field("page") String page);

    /**
     * 消息数
     *
     * @param encrypt
     * @return
     */
    @FormUrlEncoded
    @POST("api/index/messageNum")
    Observable<ResultModel<MessageNumberResp>> getNumberDigit(@Field("encrypt") String encrypt);

    /**
     * 微信支付
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/Apppay/payApply")
    Observable<ResultModel<WxPayResp>> wxPay(@Field("encrypt") String encrypt);

    /**
     * 支付宝支付  红包动态
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/Alipay/payApply")
    Observable<ResultModel<PaySelfResult>> aLiPayDynamic(@Field("encrypt") String encrypt, @Field("subject") String subject, @Field("payment") String payment);


    /**
     * 支付宝支付   购买微信号
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/Alipay/payApply")
    Observable<ResultModel<PaySelfResult>> buyWechat(@Field("encrypt") String encrypt, @Field("subject") String subject, @Field("payment") String payment, @Field("wechat") String wechat);

    /**
     * 支付宝支付   打赏红包
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/Alipay/payApply")
    Observable<ResultModel<PaySelfResult>> reward(@Field("encrypt") String encrypt, @Field("subject") String subject, @Field("payment") String payment);


    /**
     * 搜索动态详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/search/searchDynamicShow")
    Observable<ResultModel<SearchDynamicResp>> searchDynamicShow(@Field("encrypt") String encrypt, @Field("did") String did);

    /**
     * 充值金额列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/Recharge/rechargeList")
    Observable<ResultModel<RechargeAmtListResp>> rechargeAmtList(@Field("encrypt") String encrypt);

    /**
     * android充值接口
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/Alipay/androidRecharge")
    Observable<ResultModel<AndroidRechargeResp>> androidRecharge(@Field("encrypt") String encrypt, @Field("subject") String subject);

    /**
     * 充值记录
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/Recharge/rechargeRecord")
    Observable<ResultModel<List<RechargeRecordResp>>> rechargeRecord(@Field("encrypt") String encrypt, @Field("page") String page);

    /**
     * 支付方式选择
     *
     * @param encrypt
     * @return
     */
    @FormUrlEncoded
    @POST("api/order/payMethod")
    Observable<ResultModel<PaySelectResp>> selectPay(@Field("encrypt") String encrypt);

    /**
     * 易联支付 查看微信
     *
     * @param encrypt
     * @param payment   支付方式，1支付宝，2微信，3银联，4易联
     * @param type_id   1红包动态 2打赏红包 3购买微信
     * @param orderDesc 购买商品描述
     * @return
     */
    @FormUrlEncoded
    @POST("api/Easy/Merchant")
    Observable<ResultModel<YiLianPayResp>> yiLianPay(@Field("encrypt") String encrypt, @Field("payment") String payment, @Field("type_id") String type_id, @Field("orderDesc") String orderDesc);


    /**
     * 易联支付  打赏红包
     *
     * @param encrypt
     * @param payment   支付方式，1支付宝，2微信，3银联，4易联
     * @param
     * @param orderDesc 购买商品描述
     * @return
     */
    @FormUrlEncoded
    @POST("api/Easy/Merchant")
    Observable<ResultModel<YiLianPayResp>> yiLianPayLuckMoney(@Field("encrypt") String encrypt, @Field("payment") String payment, @Field("orderDesc") String orderDesc, @Field("type_id") String type_id);


    /**
     * 易联支付  打赏红包
     *
     * @param encrypt
     * @param payment   支付方式，1支付宝，2微信，3银联，4易联
     * @param
     * @param orderDesc 购买商品描述
     * @return
     */
    @FormUrlEncoded
    @POST("api/Easy/Merchant")
    Observable<ResultModel<YiLianPayResp>> yiLianPayDynamic(@Field("encrypt") String encrypt, @Field("payment") String payment, @Field("orderDesc") String orderDesc, @Field("type_id") String type_id);


    /**
     * 易联支付  直接充值
     *
     * @param encrypt
     * @param
     * @param
     * @param orderDesc 购买商品描述
     * @return
     */
    @FormUrlEncoded
    @POST("api/Easy/androidRecharge")
    Observable<ResultModel<YiLianPayResp>> yiLianRecharge(@Field("encrypt") String encrypt, @Field("orderDesc") String orderDesc);

    /**
     * 易联支付  查询后天结果
     *
     * @param orderId
     * @return
     */
    @FormUrlEncoded
    @POST("api/Easy/selectorder")
    Observable<ResultModel<YiLianCalBackResp>> yiLianCallBack(@Field("orderId") String orderId);

    /**
     * 搜索推荐
     *
     * @param encrypt
     * @return
     */
    @FormUrlEncoded
    @POST("api/search/recommendV1_2")
    Observable<ResultModel<List<SearchRecommnedListResp>>> getSearchReData(@Field("encrypt") String encrypt);

    /**
     * 邀请好友，推广列表
     *
     * @param encrypt
     * @return
     */
    @FormUrlEncoded
    @POST("api/invite/inviteUserV1_2")
    Observable<ResultModel<InvitFrientListResp>> getInvitFriendList(@Field("encrypt") String encrypt, @Field("page") String page);

    /**
     * 上传图片
     */
    @Multipart
    @POST("api/Uploads/uploads")
    Observable<ResultModel<UploadPictureResp>> uploadPicture(@Part List<MultipartBody.Part> partList);


    /**
     * 首页 热门数据
     *
     * @param encrypt
     * @param page
     * @return
     */
    @FormUrlEncoded
    @POST("api/index/indexV1_2")
    Observable<ResultModel<HotInHomeResp>> getHotData(@Field("encrypt") String encrypt, @Field("page") String page);

    /**
     * 发布动态
     */
    @Multipart
    @POST("api/dynamic/dynamicAdd_v1_2")
    Observable<ResultModel<Object>> publishDynamic(@Part List<MultipartBody.Part> partList);
}
