package com.mall.meongnyang.client.member.controller;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.mall.meongnyang.admin.marketing.vo.AdminEventAttendenceVO;
import com.mall.meongnyang.admin.marketing.vo.AdminEventVO;
import com.mall.meongnyang.client.member.service.*;
import com.mall.meongnyang.client.member.vo.ClientCustomerVO;
import com.mall.meongnyang.client.member.vo.ClientTermsAgreeVO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
public class ClientNaverLoginController
{


    @Autowired
    private ClientSelectLoginService clientSelectLoginService;

    @Autowired
    private ClientNaverLoginService clientNaverLoginService;

    @Autowired
    private NaverLoginBO naverLoginBO;

    @Autowired
    private ClientSelectAvailableEventService clientSelectAvailableEventService;
    @Autowired
    private ClientSelectEventAttendenceService clientSelectEventAttendenceService;
    @Autowired
	private ClientInsertTermsAgreeListService clientInsertTermsAgreeListService;


    private String apiResult = null;

    @Autowired
    private void setNaverLoginBO(NaverLoginBO naverLoginBO)
    {
        this.naverLoginBO = naverLoginBO;
    }


    @RequestMapping(value = "/NaverLogin.do", method = {RequestMethod.GET, RequestMethod.POST})
    @Transactional
    public String NaverLogin(Model model, @RequestParam String code, @RequestParam String state, HttpSession session, ClientCustomerVO clientCustomerVO)
            throws IOException, ParseException
    {
//			System.out.println("여기는 callback");
        OAuth2AccessToken oauthToken;
        oauthToken = naverLoginBO.getAccessToken(session, code, state);
        //로그인 사용자 정보를 읽어온다.
        apiResult = naverLoginBO.getUserProfile(oauthToken);


        model.addAttribute("result", apiResult);

        /** apiResult json 구조
         {"resultcode":"00",
         "message":"success",
         "response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
         **/
        //2. String형식인 apiResult를 json형태로 바꿈
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(apiResult);
        JSONObject jsonObj = (JSONObject) obj;
        //3. 데이터 파싱
        //Top레벨 단계 _response 파싱
        JSONObject response_obj = (JSONObject) jsonObj.get("response");
        //response의 nickname값 파싱
        String naveremail = (String) response_obj.get("email");
        String navername = (String) response_obj.get("name");

//			System.out.println(naveremail);
//			System.out.println(navername);


        ClientCustomerVO tempVO = clientSelectLoginService.selectLoginCheck(clientCustomerVO);
        if (tempVO == null)
        {
            clientCustomerVO.setCustomerTbEmail(naveremail);
            clientCustomerVO.setCustomerTbName(navername);
            clientNaverLoginService.insertNaver(clientCustomerVO);

			tempVO = clientSelectLoginService.selectLoginCheck(clientCustomerVO);

            session.setAttribute("customer", tempVO);


			ClientTermsAgreeVO clientTermsAgreeVO = new ClientTermsAgreeVO();
			clientTermsAgreeVO.setTermsTbNo(2);
			clientTermsAgreeVO.setCustomerTbNo(tempVO.getCustomerTbNo());
			clientTermsAgreeVO.setTermsAgreeTbConsentStatus("N");
			clientInsertTermsAgreeListService.insertTermsAgreeListService(clientTermsAgreeVO);

        }
        else if (tempVO != null && clientCustomerVO.getCustomerTbPassword() != null)
        {

            session.setAttribute("customer", tempVO);

            AdminEventVO event = clientSelectAvailableEventService.selectAvailableEvent(new AdminEventVO());
            if (event != null)
            {
                AdminEventAttendenceVO adminEventAttendenceVO = new AdminEventAttendenceVO();
                adminEventAttendenceVO.setCustomerTbNo(tempVO.getCustomerTbNo());
                adminEventAttendenceVO.setEventTbNo(event.getEventTbNo());
                boolean isEventAttended = clientSelectEventAttendenceService.checkAttendenceToday(adminEventAttendenceVO);
                if (isEventAttended)
                {
                    session.setAttribute("eventGo", "f");
                }
                else
                {
                    session.setAttribute("eventGo", "t");
                }
            }

            return "redirect:/index.do";
        }
        else
        {
            model.addAttribute("loginCheckSubmit", false);
            return "redirect:/index.do";
        }

        //4.파싱 닉네임 세션으로 저장
//			session.setAttribute("customer",clientCustomerVO);//세션 생성
//			
//			model.addAttribute("result", apiResult);


        return "redirect:/index.do";
    }


}
		
	