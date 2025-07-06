package com.paw.fund.core.service.features.account.controller.pub;

import com.paw.fund.core.service.bootstrap.config.rest.PValueResponse;
import com.paw.fund.core.service.features.account.controller.models.AccountRequest;
import com.paw.fund.core.service.features.account.controller.models.UpdatePasswordRequest;
import com.paw.fund.core.service.features.account.controller.models.UpdateStatusRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/v1/pub/account")
@Tag(name = "Account", description = "QL tài khoản")
public interface IAccountsPubApi {
    @PostMapping("/shelter-owner")
    PValueResponse<Long> saveShelterOwner(@RequestBody @Valid AccountRequest accountRequest);

    @PostMapping("/adopter")
    PValueResponse<Long> saveAdopter(@RequestBody @Valid AccountRequest accountRequest);

    @PatchMapping("/active")
    PValueResponse<?> active(@RequestBody @Valid UpdateStatusRequest updateStatusRequest);

    @PatchMapping("/in-active")
    PValueResponse<?> inactive(@RequestBody @Valid UpdateStatusRequest updateStatusRequest);

    @PatchMapping("/change-password")
    PValueResponse<?> changePassword(@RequestBody @Valid UpdatePasswordRequest updatePasswordRequest);
}
