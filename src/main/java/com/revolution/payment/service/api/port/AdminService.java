package com.revolution.payment.service.api.port;

import com.revolution.payment.service.api.dto.PayoutDto;
import com.revolution.payment.service.api.request.PayoutRequest;

public interface AdminService {

    PayoutDto notifyPayout(PayoutRequest request);
}
