package racingcar.domain.car;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static racingcar.domain.movement.v1.Movement.*;
import static racingcar.dummy.TestDataDummy.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import racingcar.domain.movement.v1.Movement;

@DisplayName("자동차")
class CarTest {
	@DisplayName("생성 성공 테스트")
	@ParameterizedTest(name = "carName = {0}")
	@ValueSource(strings = {"가", "가나", "가나다", "가나다라", "가나다라마"})
	void createCarSuccessTest(String carName) {
		assertDoesNotThrow(() -> Car.from(carName));
	}

	@DisplayName("이름 공백일 경우 생성 예외 발생 테스트")
	@ParameterizedTest(name = "carName = {0}")
	@NullAndEmptySource
	void createCarNameNullOrBlankExceptionTest(String carName) {
		assertThatThrownBy(() -> Car.from(carName))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("이름 5글자 초과일 경우 생성 예외 발생 테스트")
	@ParameterizedTest(name = "carName = {0}")
	@ValueSource(strings = {"123456", "1234567", "aaaaaa", "ㅁㅁㅁㅁㅁㅁㅁ"})
	void createCarNameOverLengthExceptionTest(String carName) {
		assertThatThrownBy(() -> Car.from(carName))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("이동 확인 버전 V1 테스트")
	@ParameterizedTest(name = "movement = {0}")
	@MethodSource("carMovementV1Dummy")
	void moveSuccessV1Test(Movement movement, Position expectedPosition) {
		final Car car = Car.from("헤나");

		car.moveV1(movement);
		boolean isSamePosition = car.isSamePosition(expectedPosition);

		assertThat(isSamePosition).isTrue();
	}

	@DisplayName("이동 확인 버전 V2 테스트")
	@ParameterizedTest(name = "movement = {0}")
	@MethodSource("carMovementV2Dummy")
	void moveSuccessV2Test(int moveDistance, Position expectedPosition) {
		final Car car = Car.from("헤나");

		car.moveV2(moveDistance);
		boolean isSamePosition = car.isSamePosition(expectedPosition);

		assertThat(isSamePosition).isTrue();
	}

	static Stream<Arguments> carMovementV1Dummy() {
		return Stream.of(
			Arguments.arguments(MOVE_STOP, MOVE_ZERO_POSITION),
			Arguments.arguments(MOVE_FORWARD, MOVE_FORWARD_ONCE_POSITION)
		);
	}

	static Stream<Arguments> carMovementV2Dummy() {
		return Stream.of(
			Arguments.arguments(0, MOVE_ZERO_POSITION),
			Arguments.arguments(1, MOVE_FORWARD_ONCE_POSITION)
		);
	}
}