package com.paw.fund.core.service.features.account.controller.models;


import com.paw.fund.core.service.features.account.controller.models.media.MediaRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;

public record AccountRequest(
        @NotNull(
                message = "Vui lập nhập họ tên"
        )
        @Schema(
                example = "Nguyen Van"
        )
        String firstName,


        @Schema(
                example = "test"
        )
        String avatar,


        @NotNull(
                message = "Vui lập nhập tên"
        )
        @Schema(
                example = "Be"
        )
        String lastName,


        @NotNull(
                message = "Vui lập nhập số cccd"
        )
        @Length(
                message = "Số CCCD phải có ít nhất 12 ký tự số",
                min = 12
        )
        @Schema(
                example = "012345678910"
        )
        String identification,


        @NotNull(
                message = "Vui lập nhập email"
        )
        @Pattern(
                message = "Email không hợp lệ",
                regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        )
        @Schema(
                example = "nguyenvanbe@gmail.com"
        )
        String email,

        @NotNull(
                message = "Vui lập nhập mật khẩu"
        )
        @Schema(
                example = "nguyenvanbe"
        )
        String password,


        @NotNull(
                message = "Vui lập nhập số điện thoại"
        )
        @Pattern(
                regexp = "^(?:\\+84|0)(3[2-9]|5[2689]|7[0-9]|8[1-9]|9[0-9])[0-9]{7}$",
                message = "Số điện thoại không hợp lệ"
        )
        @Schema(
                example = "0981874736"
        )
        String phone,


        @Schema(
                example = "43 Phan Van Tri, Phường 2, Quận 5"
        )
        String address,


        @Schema(
                example = "1997-01-02T00:00:00Z"
        )
        LocalDate dateOfBirth,


        @NotNull(
                message = "Vui lòng chọn thông tin giới tính"
        )
        @Schema(
                example = "MALE"
        )
        String genderCode,


        @NotNull(
                message = "Vui lòng chọn thông tin giới tính"
        )
        @Schema(
                example = "Nam"
        )
        String genderName,

        List<MediaRequest> medias
) {
}
